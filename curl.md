#### create User
`curl -s -X POST -d '{"name":"Lolik","email":"lolik@user.com","password":"123456"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@admin.com:123456`

#### disable User 10005
`curl -s -X POST -d 'enabled=false' http://localhost:8080/rest/admin/users/10005 --user admin@admin.com:123456`

#### get All Users
`curl -s http://localhost:8080/rest/admin/users --user admin@admin.com:123456`

#### get Users 10003
`curl -s http://localhost:8080/rest/admin/users/10003 --user admin@admin.com:123456`

#### delete User 10004
`curl -s -X DELETE http://localhost:8080/rest/admin/users/10004 --user admin@admin.com:123456`

#### update User 10003
`curl -s -X POST -d '{"id":10003,"name":"Sergiy","email":"sergiy@admin.com","password":"00000"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/users --user admin@admin.com:123456`

#### get All Restaurants With Vote statistic
`curl -s http://localhost:8080/rest/profile/restaurants --user admin@admin.com:123456`

#### get Restaurant 10000
`curl -s http://localhost:8080/rest/profile/restaurants/10000`

#### create Restaurant
`curl -s -X POST -d '{"name":"Bulvar"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@admin.com:123456`

#### update Restaurant 10030
`curl -s -X POST -d '{"id":10030,"name":"Puzata Hata"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@admin.com:123456`

#### delete Restaurant 10128
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/10128  --user admin@admin.com:123456`

#### get current User profile
` curl -s http://localhost:8080/rest/profile/ --user admin@admin.com:123456`

#### register User
`curl -s -X POST -d '{"name":"Register","email":"reg@user.com","password":"00000"}' -H 'Content-Type: application/json' http://localhost:8080/rest/profile/register`

#### update User profile
`curl -s -X PUT -d '{"id":10003,"name":"Tolik","email":"admin@admin.com","password":"000000"}' -H "Content-Type: application/json" http://localhost:8080/rest/profile/ --user admin@admin.com:123456`

#### create Vote
`curl -s http://localhost:8080/rest/profile/restaurants/10009/vote --user admin@admin.com:123456`