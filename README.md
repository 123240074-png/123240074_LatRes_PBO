nambahin package config yang isinya koneksi.java buat nyambungin ke MySQL
bikin DbToDoRepository.java buat nyimpen database asli, bukan ke fakeRepository lagi
Fitur yang dibuat:
getAll()
getById()
insert()
update()
deleteById()
Terus di main program ganti, yang sebelumnya new FakeTodoRepository() jadi new DbToDoRepository()
Bikin database di MySQL (latres_pbo)
nambah konektor JAR di dependencies
