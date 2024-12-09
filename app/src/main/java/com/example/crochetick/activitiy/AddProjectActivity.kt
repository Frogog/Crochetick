package com.example.crochetick.activitiy


//class AddProjectActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        val viewModel: AddProjectViewModel by viewModels()
//        setContent {
//            CrochetickTheme {
//                Scaffold (
//                    topBar = {
//                        ProjectTopBar(
//                            {finish()},
//                            {viewModel.validateForm()}
//                        )
//                    },
//                ){innerPadding->
//                    MainContent(innerPadding,{goToDetail(this)},viewModel)
//                }
//
//            }
//
//        }
//    }
//}
//fun goToDetail(context: Context){
//    val intent = Intent(context,AddDetailActivity::class.java)
//    context.startActivity(intent)
//}
//
//@Composable
//fun MainContent(innerPadding: PaddingValues, goToDetail: ()->Unit, viewModel: AddProjectViewModel = viewModel()){
//    val uiState by viewModel.uiStateProject.collectAsState()
//    Box(
//        modifier = Modifier.padding(innerPadding)
//    ){
//        Column{
//            Column(modifier = Modifier.padding(vertical = 12.dp,horizontal = 20.dp)){
//                OutlinedTextField(
//                    value = uiState.title,
//                    onValueChange = {viewModel.updateTitle(it)},
//                    label = {
//                        Row{
//                            Text("Название", color = TextSecond)
//                            Text("*", color = Color.Red)
//                        }
//                    },
//                    supportingText = if (!viewModel.rightTitle.value) {
//                        { Text("Название обязательно", color = Color.Red) }
//                    }else {
//                        { Text("", color = Color.Red) }
//                    },
//                    singleLine = true,
//                    modifier = Modifier.width(210.dp)
//                )
//                OutlinedTextField(
//                    value = uiState.description,
//                    onValueChange = {viewModel.updateDescription(it)},
//                    label = {Text("Описание", color = TextSecond)},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(112.dp),
//                    minLines = 3,
//                    maxLines = 3,
//                )
//            }
//            Row(
//                modifier = Modifier
//                    .padding(horizontal = 16.dp, vertical = 10.dp)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ){
//                Text("Детали", style = MaterialTheme.typography.bodyLarge)
//                IconButton(
//                    onClick = {
//                        goToDetail()
//                    },
//                    modifier = Modifier.size(24.dp),
//                ) {
//                    Icon(
//                        imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
//                        contentDescription = "AddDetail",
//                        tint = TextSecond
//                    )
//                }
//            }
//            if (!viewModel.rightDetails.value){
//                Text(
//                    "Добавьте хотя бы одну деталь",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = Color.Red,
//                    modifier = Modifier.padding(start = 16.dp)
//                )
//            }
//            DetailList()
//            Spacer(modifier = Modifier
//                .height(24.dp)
//                .shadow(8.dp))
//        }
//    }
//}
//
//@Composable
//fun ProjectTopBar(
//    onBackClick: () -> Unit,
//    onDoneClick: () -> Unit,
//    ){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Background)
//            .padding(top = 32.dp, bottom = 8.dp, start = 16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(end = 10.dp)
//        ) {
//            IconButton(
//                onClick = onBackClick,
//                modifier = Modifier.size(24.dp),
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
//                    contentDescription = "Localized description",
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(
//                "Добавить новый проект",
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
//        TextButton(
//            onClick = onDoneClick,
//            contentPadding = PaddingValues(0.dp),
//
//            modifier = Modifier.padding(end=12.dp) //Чуть меньше чем в дизайне
//        ) {
//            Text(
//                text = "Готово",
//                style = MaterialTheme.typography.titleMedium,
//                color = TextBright,
//            )
//        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewAddScreen(innerPadding: PaddingValues = PaddingValues(20.dp,20.dp)){
//    CrochetickTheme {
//        Scaffold (
//            topBar = { HardTopBarTest("Добавить новый проект") },
//            //bottomBar = { ProjectBottomBar()}
//        ){innerPadding->
//            Box(
//                modifier = Modifier.padding(innerPadding)
//            ){
//                Column{
//                    Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp)){
//                        var nameText by remember {
//                            mutableStateOf("")
//                        }
//                        OutlinedTextField(
//                            value = nameText,
//                            onValueChange = {nameText = it},
//                            label = {
//                                Row{
//                                    Text("Название", color = TextSecond)
//                                    Text("*", color = Color.Red)
//                                }
//                            },
//                            supportingText = {
//                                Text("Название обязательно", color = Color.Red)
//                            },
//                            singleLine = true,
//                            modifier = Modifier.width(210.dp)
//                        )
//                        var descriptionText by remember {
//                            mutableStateOf("")
//                        }
//                        OutlinedTextField(
//                            value = "descriptionText",
//                            onValueChange = {descriptionText = it},
//                            label = {Text("Описание", color = TextSecond)},
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(112.dp),
//                            minLines = 3,
//                            maxLines = 3,
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            .padding(horizontal = 16.dp, vertical = 10.dp)
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ){
//                        Text("Детали", style = MaterialTheme.typography.bodyLarge)
//                        IconButton(
//                            onClick = {},
//                            modifier = Modifier.size(24.dp),
//                        ) {
//                            Icon(
//                                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
//                                contentDescription = "AddDetail",
//                                tint = TextSecond
//                            )
//                        }
//                    }
//                    Text(
//                        "Добавьте хотя бы одну деталь",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = Color.Red,
//                        modifier = Modifier.padding(start = 16.dp)
//                    )
//                    DetailList()
//                    Spacer(modifier = Modifier
//                        .height(24.dp)
//                        .shadow(8.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DetailList(){
//    val DetailDataArrays:List<DetailData> = listOf(
//        DetailData(0,"Ухо"),
//        DetailData(1,"Тело"),
//        DetailData(2,"Лапы")
//    )
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        itemsIndexed(DetailDataArrays){index, item ->
//            DetailCard(
//                item = item,
//            )
//        }
//    }
//}
//
//@Composable
//fun DetailCard(item: DetailData){
//    Card(
//        shape = RectangleShape,
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.background
//        )
//    ){
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 24.dp, vertical = 10.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Text(item.title, style = MaterialTheme.typography.bodyLarge)
//            IconButton(
//                onClick = {},
//                modifier = Modifier.size(24.dp),
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(R.drawable.minus_icon),
//                    contentDescription = "AddDetail",
//                    tint = TextSecond
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun HardTopBar(title:String, navController: NavController){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Background)
//            .padding(top = 32.dp, bottom = 8.dp, start = 16.dp, end = 4.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(end = 10.dp)
//        ) {
//            IconButton(
//                onClick = {navController.popBackStack()},
//                modifier = Modifier.size(24.dp),
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
//                    contentDescription = "Localized description"
//                )
//            }
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
//        TextButton(
//            onClick = {
//
//                navController.popBackStack()
//            },
//        ) {
//            Text(
//                text = "Готово",
//                style = MaterialTheme.typography.titleMedium,
//                color = TextBright,
//            )
//        }
//    }
//}
//
//@Composable
//fun HardTopBarTest(title:String){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Background)
//            .padding(top = 32.dp, bottom = 8.dp, start = 16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(end = 10.dp)
//        ) {
//            IconButton(
//                onClick = {
//                },
//                modifier = Modifier.size(24.dp),
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
//                    contentDescription = "Localized description",
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
//        TextButton(
//            onClick = {
//            },
//            contentPadding = PaddingValues(0.dp),
//
//            modifier = Modifier.padding(end=12.dp) //Чуть меньше чем в дизайне
//        ) {
//            Text(
//                text = "Готово",
//                style = MaterialTheme.typography.titleMedium,
//                color = TextBright,
//            )
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewTopBar(){
//    HardTopBarTest("Добавить новый проект")
//}