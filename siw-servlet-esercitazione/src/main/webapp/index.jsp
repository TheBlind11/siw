<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Prima Webapp</title>
  </head>
<body>
  <form action="persona" method="post">
    <div> Nome: <input type="text" name="nome" value="${nome}" /> </div>
    <div> ${messaggioErroreNome} </div>
    <div> Cognome: <input type="text" name="cognome" value="${cognome}" /> </div>
    <div> ${messaggioErroreCognome} </div>
    <div> <input type="submit" 
          name="sumbit" value="invia" /> </div>
  </form>
</body>
</html>