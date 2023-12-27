package eu.tutorials.noteapp.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier:Modifier = Modifier,
    text: String,
    label:String,
    maxLine:Int = 1,
    onTextChange: (String) -> Unit,
    oneImeAction:() -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label)},
//imeAction-attribute defines what action the system should take when the user interacts with the input method editor
//imeAction.Done-When the user presses the done button
//keyboardActions - execute what will happen as soon as the user pressed the done button
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            oneImeAction()
            //declaring keyboardDone function inside NotScreen when clicking the done button
            keyboardController?.hide()
        }),
        modifier = modifier
    )

}