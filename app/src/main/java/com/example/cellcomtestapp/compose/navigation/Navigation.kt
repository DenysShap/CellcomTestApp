package com.example.cellcomtestapp.compose.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.cellcomtestapp.compose.detail.ui.MovieDetailsScreen
import com.example.cellcomtestapp.compose.detail.ui.YoutubePlayerScreen
import com.example.cellcomtestapp.compose.detail.viewmodel.MovieDetailsViewModel
import com.example.cellcomtestapp.compose.favourite.ui.FavouriteScreen
import com.example.cellcomtestapp.compose.favourite.viewmodel.FavouriteViewModel
import com.example.cellcomtestapp.compose.home.ui.HomeScreen
import com.example.cellcomtestapp.compose.home.viewmodel.HomeViewModel
import com.example.cellcomtestapp.compose.navigation.model.BottomNavItem
import com.example.cellcomtestapp.compose.navigation.model.Screens
import com.example.cellcomtestapp.compose.navigation.provider.provideBottomNavigationItems
import com.example.cellcomtestapp.compose.theme.Purple40

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navItems = provideBottomNavigationItems()

    when (navBackStackEntry?.destination?.route) {
        Screens.Home.route -> bottomBarState.value = true
        Screens.Favourites.route -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (bottomBarState.value) {
                BottomNavBar(navItems, currentDestination, navController)
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            composable<Screens.Home> {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                val state = homeViewModel.homeState.collectAsStateWithLifecycle()
                HomeScreen(
                    homeState = state.value,
                    onEvent = homeViewModel::onEvent,
                    onItemClick = { movieDetailArgs ->
                        navController.navigate(
                            Screens.MovieDetailsScreen(
                                movieId = movieDetailArgs.movieId,
                                movieTitle = movieDetailArgs.movieTitle
                            )
                        )
                    }
                )
            }
            composable<Screens.Favourites> {
                val homeViewModel = hiltViewModel<FavouriteViewModel>()
                val state = homeViewModel.favouriteState.collectAsStateWithLifecycle()
                FavouriteScreen(
                    favouriteMoviesState = state.value,
                    onEvent = homeViewModel::onEvent
                ) {
                    navController.popBackStack()
                }
            }

            composable<Screens.MovieDetailsScreen> {
                val arg = it.toRoute<Screens.MovieDetailsScreen>()
                val movieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()
                movieDetailsViewModel.initialize(movieId = arg.movieId, movieTitle = arg.movieTitle)
                val state = movieDetailsViewModel.movieDetailsState.collectAsStateWithLifecycle()
                MovieDetailsScreen(
                    movieDetailsState = state.value,
                    onEvent = movieDetailsViewModel::onEvent,
                    onViewTrailerClick = { youtubePlayerArgs ->
                        navController.navigate(
                            Screens.YoutubePlayerScreen(
                                youtubeCode = youtubePlayerArgs.youtubeCode
                            )
                        )
                    }, {
                        navController.popBackStack()
                    }
                )

            }
            composable<Screens.YoutubePlayerScreen> {
                val arg = it.toRoute<Screens.YoutubePlayerScreen>()
                YoutubePlayerScreen(youtubeCode = arg.youtubeCode)
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    navItems: List<BottomNavItem>,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    Column {
        Row {
            NavigationBar(containerColor = Color.Transparent) {
                navItems
                    .forEach { navigationItem ->
                        val isSelected = currentDestination?.route == navigationItem.route
                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(
                                    text = navigationItem.label,
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            },
                            icon = {
                                Icon(
                                    ImageVector.vectorResource(id = navigationItem.icon),
                                    contentDescription = navigationItem.label,
                                )
                            },
                            onClick = {
                                navController.navigate(navigationItem.correspondingScreen) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Purple40,
                                selectedTextColor = Purple40,
                                selectedIndicatorColor = Color.Transparent,
                                unselectedIconColor = Black,
                                unselectedTextColor = Black,
                                disabledIconColor = Black,
                                disabledTextColor = Black,
                            ),
                        )
                    }
            }
        }
    }
}