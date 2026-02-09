package com.example.collegefestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    // Scroll state for the main screen
    val scrollState = rememberScrollState()
    
    // Toggle State
    var selectedTab by remember { mutableStateOf("Udaan (College)") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)) // Dark Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            // --- Header ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Profile Placeholder
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Good Evening,",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Guest",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                IconButton(onClick = { /* TODO: Info/Settings */ }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome to",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(
                text = "Global Group Of Institutes",
                color = Color(0xFF8B5CF6), // Purple
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Toggle Switch ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFF1E293B)) // Darker slate
            ) {
                TabButton(
                    text = "Shrishti (School)",
                    isSelected = selectedTab == "Shrishti (School)",
                    modifier = Modifier.weight(1f),
                    onClick = { selectedTab = "Shrishti (School)" }
                )
                TabButton(
                    text = "Udaan (College)",
                    isSelected = selectedTab == "Udaan (College)",
                    modifier = Modifier.weight(1f),
                    onClick = { selectedTab = "Udaan (College)" }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (selectedTab == "Udaan (College)") {
                var showPosterDialog by remember { mutableStateOf(false) }

                if (showPosterDialog) {
                    Dialog(onDismissRequest = { showPosterDialog = false }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Black)
                        ) {
                            androidx.compose.foundation.Image(
                                painter = androidx.compose.ui.res.painterResource(id = com.example.collegefestapp.R.drawable.event_poster),
                                contentDescription = "Event Poster",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                            IconButton(
                                onClick = { showPosterDialog = false },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }

                // --- Hero Card (Ninja) ---
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val scale by animateFloatAsState(
                    targetValue = if (isPressed) 0.90f else 1f, // Slightly deeper press
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "scale"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        ) { showPosterDialog = true }, // Make entire card clickable
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Background Image
                        androidx.compose.foundation.Image(
                            painter = androidx.compose.ui.res.painterResource(id = com.example.collegefestapp.R.drawable.hero_card_bg),
                             contentDescription = "Ninja Star Night",
                             contentScale = ContentScale.Crop, // Crop to fill the card
                             modifier = Modifier.fillMaxSize()
                        )

                        // Gradient Overlay for Readability
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        )
                                    )
                                )
                        )

                        // Content Overlay
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(20.dp)
                        ) {
                             // Tag
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFF8B5CF6).copy(alpha = 0.9f), // Purple
                                                Color(0xFFD946EF).copy(alpha = 0.9f)  // Pink
                                            )
                                        )
                                    )
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = "Global Group of Institutes",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = "Star Night - Ninja",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            
                            Text(
                                text = "7:00 PM - 10:00 PM",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- Quick Actions ---
                Text(
                    text = "Quick Actions",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1E293B)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home, // Using Home icon as placeholder for Schedule
                            contentDescription = "Schedule",
                            tint = Color(0xFFA855F7), // Purple tint
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Schedule",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- Event Highlights ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                     Text(
                        text = "Event Highlights",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "View Schedule",
                        color = Color(0xFF3B82F6),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { }
                    )
                }
               
                Spacer(modifier = Modifier.height(16.dp))

                 LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(listOf("Technical Events", "Cultural Events", "Literary Events")) { name ->
                        HighlightCard(title = name)
                    }
                }
            } else {
                // Empty State for Shrishti
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Coming Soon",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) Brush.horizontalGradient(
                    colors = listOf(Color(0xFFA855F7), Color(0xFFD946EF)) // Purple to Pink
                ) else androidx.compose.ui.graphics.SolidColor(Color.Transparent)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun HighlightCard(title: String) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)) // Dark background
    ) {
         Box(
             modifier = Modifier
                 .fillMaxSize()
                 .background(
                     Brush.verticalGradient(
                         colors = listOf(
                             Color(0xFF1E293B),
                             Color(0xFF0F172A)
                         )
                     )
                 )
         ) {
             Column(
                 modifier = Modifier
                     .align(Alignment.BottomStart)
                     .padding(16.dp)
             ) {
                 Text(
                     text = title,
                     color = Color.White,
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Bold
                 )
                 Text(
                     text = "Code War, Robo Race...", // Placeholder desc
                     color = Color.Gray,
                     fontSize = 12.sp
                 )
             }
         }
    }
}
