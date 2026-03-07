package com.example.practice_1.navigation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.BrownWarm
import com.example.practice_1.ui.theme.OliveDark
import com.example.practice_1.ui.theme.White

@Composable
fun BottomNavigationPanel(selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit)
{
    Card(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.08f),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = BrownWarm
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = White
        )
    ) { Row(
        Modifier
            .fillMaxWidth()
            .background(BrownWarm),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        ItemNav(
            item = NavItem.Home,
            selected = selectedItem== NavItem.Home,
            onClick = {onItemSelected(NavItem.Home)},
            icon = Icons.Default.Home
        )
        ItemNav(
            item = NavItem.Gallery,
            selected = selectedItem== NavItem.Gallery,
            onClick = {onItemSelected(NavItem.Gallery)},
            icon = Icons.Default.AccountBox
        )
        ItemNav(
            item = NavItem.Detail,
            selected = selectedItem== NavItem.Detail,
            onClick = {onItemSelected(NavItem.Detail)},
            icon = Icons.Default.DateRange
        )
        ItemNav(
            item = NavItem.PersonalCard,
            selected = selectedItem== NavItem.PersonalCard,
            onClick = {onItemSelected(NavItem.PersonalCard)},
            icon = Icons.Default.AccountCircle
        )
    }
    }
}

@Composable
fun ItemNav(item: NavItem,
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector
)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(64.dp)
            .height(56.dp)
    )
    {
        if (selected) {
            Box(
                modifier = Modifier.width(38.dp)
                    .height(8.dp)
                    .background(
                        color = OliveDark,
                        shape = RoundedCornerShape(50)
                    )
            )
        } else {
            Spacer(Modifier.height(3.dp))
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            IconButton(
                onClick = { onClick() },
                modifier = Modifier.size(48.dp)
                    .padding(top = 5.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = White
                )
            ) {
                Icon(
                    imageVector  = icon,
                    contentDescription = item.title,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationPanelPreview() {
    var selectedItem by remember { mutableStateOf<NavItem>(NavItem.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigationPanel(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1E))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Выбран: ${selectedItem.title}",
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}