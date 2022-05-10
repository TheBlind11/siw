<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>Dati Persona</title>
</head>
<body>
	<form action="conferma" method="post">
		<h1>Controlla i dati inseriti:</h1>
		<div>Nome: ${nome}</div>
		<div>Cognome: ${cognome}</div>
		<div>
			<input type="submit" name="submit" value="conferma" />
		</div>
		<div>
			<input type="submit" name="back" value="torna indietro" />
		</div>
	</form>
</body>
</html>
