package com.example.meditationbiorefactoring.feature_music.presentation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_music.presentation.components.MusicItem
import com.example.meditationbiorefactoring.feature_music.presentation.components.PlayerBar

@Composable
fun MusicScreen(viewModel: MusicViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    LazyColumn {
        items(20) {
            //all string hardcoded and must implemented later
            MusicItem(
                image = null,
                title = "title",
                author = "author",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .size(120.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = LocalIndication.current,) {
                    }
            )
        }
    }
    //state.currentTrack?.let {
        PlayerBar(
            //all string hardcoded and must implemented later
            image = null,
            title = "title",
            author = "author",
            isPlaying = false,
            buttonIcon = Icons.Default.PlayArrow,
            onPlayPause = {
                /*implement later*/
            },
        )
    //}
}