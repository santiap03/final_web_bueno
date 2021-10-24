window.onload = start;

var usuarios;

function start(){
	usuarios= petition();

	let log = document.getElementById("login");
	log.onclick= function print(){
		console.log("login");
		petition2();
	}
	let del = document.getElementById("delbtn");
	del.onclick= function print(){
		console.log("delete");
		petition3();
	}
	if(sessionStorage.getItem('logged')!= null ){
		console.log("logged")
		var myobj = document.getElementById("log");
		myobj.remove();
		var d1 = document.getElementById('reg');
		//d1.insertAdjacentHTML('afterend', '<a class="btn btn-primary bg-white text-dark" href="index_MiCuenta.html" id="cuenta">Mi cuenta</a>');
		d1.insertAdjacentHTML('afterend', '<a class="btn btn-primary bg-white text-dark" href="index_main.html" id="logout">Logout</a>');
		document.getElementById("logout").onclick = function l(){logout();console.log("Logged out")}
		if(sessionStorage.getItem('logged')==="admin"){
			d1.insertAdjacentHTML('afterend', '<a class="btn btn-primary bg-white text-dark" href="index_usuarios.html" id="usuarios">Usuarios</a>');
		}
	}


}

function search(){
	const list1= ["inicio", "mision", "publico"]
	const list2= ["comienzo", "beneficio", "beneficios", "razones", "consejos"]
	const list3= ["libros", "datos", "lista"]
	const list4= ["sugerencia", "queja", "quejas", "reclamo", "reclamos"]
	const list5= ["registro", "inscripcion"]
	const list6= ["personal", "cuenta"]
	var obje = (document.getElementById("sbar")).value;
	console.log(obje);
	if(list1.indexOf(obje) > -1){
		console.log("lo encontro en 1")
		window.location.href = "index_main.html";
	}
	else if(list2.indexOf(obje) > -1){
		console.log("lo encontro en 2")
		window.location.href = "index_Motivacion.html";
	}
	else if(list3.indexOf(obje) > -1){
		console.log("lo encontro en 3")
		window.location.href = "index_libros.html";
	}
	else if(list4.indexOf(obje) > -1){
		console.log("lo encontro en 4")
		window.location.href = "index_sugerencias.html";
	}
	else if(list5.indexOf(obje) > -1){
		console.log("lo encontro en 5")
		window.location.href = "index_registro.html";
	}
	else if(list6.indexOf(obje) > -1){
		console.log("lo encontro en 6")
		window.location.href = "index_libros.html";
	};

};
async function petition(){
	console.log("haciendo peticion");
	let url = "http://192.168.1.6:8080/clientes";
	var response;
	try {
		response = await  fetch(url);

	}
	catch (err) {
		console.log('fetch failed', err);

	}
	const a = await response.json();

	console.log(a); // parses JSON response into native JavaScript objects
	//location.reload();
	tabla(a);
	return a;
}
class Cliente {
	constructor(usuarioId, nombre, Edad, correo, password, rol) {
		this.usuarioId= usuarioId;
		this.nombre= nombre;
		this.Edad= Edad;
		this.correo = correo;
		this.password = password;
		this.rol = rol;
	}
}
async function petition2(){
	console.log("haciendo peticion");
	let usuarioId= parseInt((document.getElementById("idlog").value));
	let password = document.getElementById("idpass").value;
	console.log(password);
	let data = new LoginDto(usuarioId, password);
	console.log(data);
	let url = "http://192.168.1.6:8080/clientes/login";
	var response;
	try {
		response = await  fetch(url, {
			method: 'POST', // *GET, POST, PUT, DELETE, etc.
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			redirect: 'follow', // manual, *follow, error
			body: JSON.stringify(data) // body data type must match "Content-Type" header
		});

	}
	catch (err) {
		console.log('fetch failed', err);

	}
	const a = await response.text();
	if(a=="usuario" || a== "admin"){
		console.log("es un usuario");
		sessionStorage.setItem('logged', a);
	}
	console.log(a); // parses JSON response into native JavaScript objects
	location.reload();
	return a;
}
function tabla(usuarios){
	for (let i = 0; i < usuarios.length; i++) {
		var d1 = document.getElementById('lstart');
		d1.insertAdjacentHTML('afterend', '<li><a href="#" class="list-group-item bg-dark text-white"><div class="row"><div class="col"><p>'+usuarios[i].nombre+'</p> </div><div class="col"><p>'+usuarios[i].usuarioId+'</p></div></div></a></li>');

	}
}

 function petition3(){
	console.log("haciendo peticion");
	let usuarioId= parseInt((document.getElementById("idel").value));
	let password = "";
	let data = new LoginDto(usuarioId, password);
	console.log(data);
	let url = "http://192.168.1.6:8080/clientes/delete";
	var response;
	try {
		response =   fetch(url, {
			method: 'POST', // *GET, POST, PUT, DELETE, etc.
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			redirect: 'follow', // manual, *follow, error
			body: JSON.stringify(data) // body data type must match "Content-Type" header
		});

	}
	catch (err) {
		console.log('fetch failed', err);

	}
	const a =  response.json();

	console.log(a); // parses JSON response into native JavaScript objects
	location.reload();
	return a;
}

class LoginDto {
	constructor(usuarioId,password) {
		this.usuarioId= usuarioId;
		this.password = password;

	}
}