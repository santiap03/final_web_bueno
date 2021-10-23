window.onload = start;

var elements;
function start(){
	let send = document.getElementById("submit");
	console.log("agregado el evento");
	send.onclick= function print(){
		console.log("hola");
		petition();
	}
	let log = document.getElementById("login");
	log.onclick= function print(){
		console.log("hola");
		petition2();
	}
	if(sessionStorage.getItem('logged')!= null ){
		console.log("hola")
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
    elements= document.forms[0].elements;
    elements[1].onkeypress= restriction;
    elements[3].onkeypress= restriction2;
    elements[11].onkeypress= restriction1;
    
}

function restriction(event) {
	var letra = String.fromCharCode(event.charCode);
    console.log(letra);
	if (letra>='a' && letra<'z'){
        console.log(letra);
	    return true;
	}
    else{
        return false;
    }
	
}
function restriction1(event) {
	var letra = String.fromCharCode(event.charCode);


	var caractersPermitidos = "abcdefghijklmnopqrstuvwxyz123456789@.";
	// console.log(caractersPermitidos.indexOf(letra));
	return caractersPermitidos.indexOf(letra) != -1;
}
function restriction2(event) {
	var letra = String.fromCharCode(event.charCode);
	var caractersPermitidos = "1234567890";
	// console.log(caractersPermitidos.indexOf(letra));
	return caractersPermitidos.indexOf(letra) != -1;
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
function petition(){
	console.log("haciendo peticion");
	// Opciones por defecto estan marcadas con un *
	let nombre = document.getElementById("rname").value;
	let usuarioId= parseInt(document.getElementById("usuarioId").value);
	let edad = parseInt(document.getElementById("edad").value);
	let correo = document.getElementById("correo").value;
	let password = document.getElementById("pass").value;
	console.log(password);
	console.log(typeof (password));
	let data = new Cliente(usuarioId, nombre, edad, correo, password, "usuario");
	console.log(JSON.stringify(data));
	let url = "http://192.168.1.6:8080/clientes";
	const response =  fetch(url, {
		method: 'POST', // *GET, POST, PUT, DELETE, etc.
		headers: {
			'Content-Type': 'application/json'
			// 'Content-Type': 'application/x-www-form-urlencoded',
		},
		redirect: 'follow', // manual, *follow, error
		body: JSON.stringify(data) // body data type must match "Content-Type" header
	});

	console.log(response.json()); // parses JSON response into native JavaScript objects
	location.reload();
	return false;
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