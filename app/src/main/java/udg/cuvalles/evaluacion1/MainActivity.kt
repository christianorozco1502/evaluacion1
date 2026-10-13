
package udg.cuvalles.evaluacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaPrincipal()
            }
        }
    }
}

@Composable
fun PantallaPrincipal() {
    val azul = Color(0xFF1565C0)
    val opciones = (1..8).map { "Opción $it" }
    val elementos = (1..10).map { "Elemento $it" }

    var mensaje by remember {
        mutableStateOf("Bienvenido a mi aplicación")
    }

    val imagen = remember {
        val bitmap = Bitmap.createBitmap(
            120, 120, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val pintura = Paint(Paint.ANTI_ALIAS_FLAG)

        pintura.color = android.graphics.Color.rgb(187, 222, 251)
        canvas.drawCircle(60f, 60f, 58f, pintura)

        pintura.color = android.graphics.Color.rgb(21, 101, 192)
        canvas.drawCircle(60f, 43f, 22f, pintura)
        canvas.drawOval(22f, 70f, 98f, 112f, pintura)

        bitmap.asImageBitmap()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6FA))
            .padding(16.dp)
    ) {
        Text(
            text = "Evaluación Práctica 1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = azul
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Aplicación con Jetpack Compose",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = imagen,
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Mi tarjeta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Diseño de interfaces móviles",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Button(
                            onClick = {
                                mensaje = "Botón 1 presionado"
                            },
                            contentPadding = PaddingValues(
                                horizontal = 10.dp,
                                vertical = 4.dp
                            )
                        ) {
                            Text("Botón 1", fontSize = 12.sp)
                        }

                        OutlinedButton(
                            onClick = {
                                mensaje = "Botón 2 presionado"
                            },
                            contentPadding = PaddingValues(
                                horizontal = 10.dp,
                                vertical = 4.dp
                            )
                        ) {
                            Text("Botón 2", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = mensaje,
            fontSize = 14.sp,
            color = azul
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Lista horizontal - LazyRow",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(opciones) { opcion ->
                Card(
                    modifier = Modifier
                        .size(90.dp)
                        .clickable {
                            mensaje = "Seleccionaste $opcion"
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFBBDEFB)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = opcion,
                            fontSize = 14.sp,
                            color = azul
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Lista vertical - LazyColumn",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(elementos) { elemento ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            mensaje = "Seleccionaste $elemento"
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Text(
                        text = elemento,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
