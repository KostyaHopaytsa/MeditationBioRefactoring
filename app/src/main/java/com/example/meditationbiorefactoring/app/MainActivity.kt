package com.example.meditationbiorefactoring.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.meditationbiorefactoring.app.navigation.AppNavHost
import com.example.meditationbiorefactoring.ui.theme.MeditationBioRefactoringTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationBioRefactoringTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
