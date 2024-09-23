package com.demo.mykotlindemo2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.mykotlindemo2.ui.theme.MyApplicationDemo2Theme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationDemo2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier){
    
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.0) }
    var oConversionFactor = remember { mutableStateOf(1.0) }

fun unitConversion(){
    val inputValueOutput = inputValue.toDoubleOrNull() ?: 0.0
    var result = (inputValueOutput * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
    outputValue =result.toString()

}
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Moto!",
            modifier = modifier)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue = it
            unitConversion()
        }, label = { Text(text = "Enter Value")} )
        Spacer(modifier = Modifier.height(10.dp))

        val context =  LocalContext.current
        var liveData = remember { mutableStateOf("")}
        //var liveData by remember { mutableStateOf("")}//here we require to pass ' .value '
        Row {
            Box {
                Button(onClick = {
                    //liveData.value = "left"
                    inputExpanded = true
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        inputExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        inputExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        inputExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                        inputExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.value = 0.001
                        unitConversion()
                    })
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box {
                Button(onClick = {
                    //liveData.value = "left"
                    outputExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        outputExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        outputExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.0
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        outputExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        unitConversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                        outputExpanded = false
                        outputUnit = "Millimeters"
                        oConversionFactor.value = 0.001
                        unitConversion()
                    })
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Result : ${outputValue}",)
    }

}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
        UnitConverter()
}