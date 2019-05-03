#### create User
`curl -s -X POST -d '{"name":"Lolik","email":"lolik@user.com","password":"123456"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@admin.com:111111`

#### get All Users
`curl -s http://localhost:8080/rest/admin/users --user admin@admin.com:111111`

#### get Users 10003
`curl -s http://localhost:8080/rest/admin/users/10003 --user admin@admin.com:111111`

#### delete User 10064
`curl -s -X DELETE http://localhost:8080/rest/admin/users/10064 --user admin@admin.com:111111`

#### update User 10003
`curl -s -X POST -d '{"id":10003,"name":"Sergiy","email":"sergiy@admin.com","password":"00000"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/users --user admin@admin.com:111111`

#### get All Restaurants
`curl -s http://localhost:8080/rest/profile/restaurants --user admin@admin.com:111111`

#### get Restaurant 10000
`curl -s http://localhost:8080/rest/profile/restaurants/10000`

#### create Restaurant
`curl -s -X POST -d '{"name":"IlMolino"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@admin.com:111111`

#### update Restaurant 10128
`curl -s -X POST -d '{"id":10128,"name":"Puzata Hata"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@admin.com:111111`

#### delete Restaurant 10128
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/10128  --user admin@admin.com:111111`

#### get current User profile
` curl -s http://localhost:8080/rest/profile/ --user admin@admin.com:111111`

#### register User
`curl -s -X POST -d '{"name":"Register","email":"reg@user.com","password":"00000"}' -H 'Content-Type: application/json' http://localhost:8080/rest/profile/register`

#### update User profile
`curl -s -X PUT -d '{"id":10003,"name":"Tolik","email":"admin@admin.com","password":"000000"}' -H "Content-Type: application/json" http://localhost:8080/rest/profile/ --user admin@admin.com:111111`
