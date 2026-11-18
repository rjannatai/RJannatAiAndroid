package com.rjannatai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF111329)
private val Purple = Color(0xFF7657F6)
private val SurfaceDark = Color(0xFF20223D)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RJannatAiApp() }
    }
}

@Composable
fun RJannatAiApp() {
    var selected by remember { mutableStateOf("Home") }
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Purple,
            background = Ink,
            surface = SurfaceDark,
            onPrimary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Ink,
            bottomBar = {
                NavigationBar(containerColor = SurfaceDark) {
                    listOf("Home", "Chat", "Tools", "Profile").forEach { item ->
                        NavigationBarItem(
                            selected = selected == item,
                            onClick = { selected = item },
                            icon = {
                                Icon(
                                    when (item) {
                                        "Home" -> Icons.Default.Home
                                        "Chat" -> Icons.Default.Chat
                                        "Tools" -> Icons.Default.AutoAwesome
                                        else -> Icons.Default.Person
                                    }, contentDescription = item
                                )
                            },
                            label = { Text(item) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                indicatorColor = Purple,
                                unselectedIconColor = Color.LightGray,
                                unselectedTextColor = Color.LightGray
                            )
                        )
                    }
                }
            }
        ) { padding ->
            when (selected) {
                "Home" -> HomeScreen(Modifier.padding(padding)) { selected = it }
                "Chat" -> ChatScreen(Modifier.padding(padding))
                "Tools" -> ToolsScreen(Modifier.padding(padding))
                else -> ProfileScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onOpen: (String) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 28.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Text("RJannatAi", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text("One AI. Infinite Possibilities.", color = Color.LightGray)
        }
        item {
            Box(
                Modifier.fillMaxWidth().background(
                    Brush.linearGradient(listOf(Purple, Color(0xFF392A75))),
                    RoundedCornerShape(24.dp)
                ).padding(22.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Your AI workspace", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Create, learn and explore with AI.", color = Color.White.copy(alpha = .85f))
                    Button(onClick = { onOpen("Chat") }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Purple)) {
                        Text("Start chatting")
                    }
                }
            }
        }
        item { Text("Explore AI tools", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
        items(toolItems) { tool ->
            ToolCard(tool) { onOpen(if (tool.title == "AI Chat") "Chat" else "Tools") }
        }
    }
}

data class ToolItem(val title: String, val subtitle: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
val toolItems = listOf(
    ToolItem("AI Chat", "Ask questions and get help", Icons.Default.Chat),
    ToolItem("AI Image", "Create images from text prompts", Icons.Default.Image),
    ToolItem("AI Video", "Plan and create video concepts", Icons.Default.Movie),
    ToolItem("AI Voice", "Speech and voice tools", Icons.Default.RecordVoiceOver),
    ToolItem("AI Writer", "Write, rewrite and summarize", Icons.Default.Edit),
    ToolItem("AI Translate", "Bengali, English and Hindi", Icons.Default.Translate)
)

@Composable
fun ToolCard(tool: ToolItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(tool.icon, contentDescription = null, tint = Color(0xFFB7A9FF), modifier = Modifier.size(30.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(tool.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(tool.subtitle, color = Color.LightGray, fontSize = 13.sp)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    var prompt by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf("Hello! I’m RJannatAi. How can I help you today?")) }
    Column(modifier.fillMaxSize().padding(18.dp)) {
        Text("AI Chat", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Demo interface — connect your secure AI backend to enable responses.", color = Color.LightGray, fontSize = 12.sp)
        Spacer(Modifier.height(16.dp))
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(messages) { message ->
                Surface(color = SurfaceDark, shape = RoundedCornerShape(16.dp)) {
                    Text(message, Modifier.padding(14.dp))
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = prompt, onValueChange = { prompt = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message RJannatAi...") },
                shape = RoundedCornerShape(18.dp),
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = {
                if (prompt.isNotBlank()) {
                    messages = messages + "You: $prompt" + "Demo: Your message is ready to send when the backend is connected."
                    prompt = ""
                }
            }) { Icon(Icons.Default.Send, contentDescription = "Send", tint = Color(0xFFB7A9FF)) }
        }
    }
}

@Composable
fun ToolsScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("AI Studio", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Choose a creative tool", color = Color.LightGray)
        toolItems.drop(1).forEach { ToolCard(it) { } }
        Text("These are starter screens. Connect each tool to a provider through your backend.", color = Color.LightGray, fontSize = 12.sp)
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Profile & Settings", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("RJannatAi account", color = Color.LightGray)
        listOf("Language: Bengali / English / Hindi", "Theme: Dark", "Chat history", "Privacy & security", "About RJannatAi • Version 1.0.0").forEach {
            Surface(color = SurfaceDark, shape = RoundedCornerShape(14.dp)) {
                Text(it, Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}
