Remember is a composable function that can be used to cache expensive operations. You can think of it
as a cache which is local to your composable.

val state: Int = remember { 1 } The state in the above code is immutable. If you want to change that
state and also update the UI, you can use a MutableState. Compose will observe any reads/writes the
MutableState object and trigger a recomposition to update the UI.

val state: MutableState<Int> = remember { mutableStateOf(1) }

Text(
modifier = Modifier.clickable { state.value += 1 }, text = "${state.value}",
)
Another variant (added in alpha12) called rememberSaveable which is similar to remember, but the
stored value can survive process death or configuration changes.

val state: MutableState<Int> = rememberSaveable { mutableStateOf(1) } Note: You can also use
property delegates as a syntactic sugar to unwrap the MutableState.

var state: Int by remember { mutableStateOf(1) } Regarding the last part of your question:

val text = mutableStateOf("")
MutableState is an alternative to using LiveData or Flow. Compose does not observe any changes to
this object by default and therefore no recomposition will happen. If you want the changes to be
observed and the state to be cached use remember. If you don't need the caching but only want to
observe, you can use derivedStateOf. Here is a sample of how to use it.

As pointed out by Ahmad Hamwi in the comments and quoting them: