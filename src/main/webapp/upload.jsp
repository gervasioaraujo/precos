<form enctype="multipart/form-data" action="<%=request.getContextPath()%>/rest/produtos"
	method="POST">
	<input name="file" type="file" />
	<br /><br />
	<input type="submit" value="Enviar arquivo" />
</form>
