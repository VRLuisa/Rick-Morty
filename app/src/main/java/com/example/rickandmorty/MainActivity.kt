package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.ui.screen.CharacterDetailScreen
import com.example.rickandmorty.ui.screen.HomeScreen
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()

                Scaffold { innerPadding -> // Scaffold nos da PaddingValues
                    Surface(color = MaterialTheme.colorScheme.background) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            // Configuración de HomeScreen con paddingValues
                            composable("home") {
                                HomeScreen(
                                    innerPadding = innerPadding, // Usamos el innerPadding de Scaffold
                                    navController = navController
                                )
                            }

                            // Configuración de CharacterDetailScreen con argumento characterId
                            composable(
                                route = "character_detail/{characterId}",
                                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
                            ) { backStackEntry ->
                                val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
                                CharacterDetailScreen(
                                    id = characterId,
                                    innerPaddingValues = PaddingValues(0.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


