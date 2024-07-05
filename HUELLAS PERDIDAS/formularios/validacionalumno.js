const formulario=document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
    curp: /^([A-Z][AEIOU][A-Z]{2}\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/,
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, //Letras y espacios, pueden llevar acentos.
	primerp: /^[a-zA-ZÀ-ÿ\s]{1,15}$/, //Letras y espacios, pueden llevar acentos.
    segundop: /^[a-zA-ZÀ-ÿ\s]{1,15}$/, //Letras y espacios, pueden llevar acentos.
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	pass: /^.{4,12}$/ // 4 a 12 digitos.
}

const campos = {
	curp: false,
    nombre: false,
    primerp: false,
    segundop: false,
    correo: false,
    pass: false
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "curp":
			validarCampo(expresiones.curp, e.target, 'curp');
		break;
		case "nombre":
			validarCampo(expresiones.nombre, e.target, 'nombre');
		break;
		case "primerp":
			validarCampo(expresiones.primerp, e.target, 'primerp');
		break;
		case "segundop":
			validarCampo(expresiones.segundop, e.target, 'segundop');
		break;
		case "correo":
			validarCampo(expresiones.correo, e.target, 'correo');
		break;
		case "pass":
			validarCampo(expresiones.pass, e.target, 'pass');
		break;
		case "passconf":
			validarPassconf();
		break;
		
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}
const validarPassconf = () => {
	const inputPassword1 = document.getElementById('pass');
	const inputPassword2 = document.getElementById('passconf');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo__passconf`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__passconf`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__passconf .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos['pass'] = false;
	} else {
		document.getElementById(`grupo__passconf`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__passconf`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__passconf .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos['pass'] = true;
	}
}



inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});