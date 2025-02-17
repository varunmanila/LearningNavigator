//Create Subject
http://localhost:30080/api/v1/subject/save

//method POST
//Payload for api 
{
"name":"Maths",
"studentIds":[],
"examIds": []
}

//Update Subject
//method PUT
http://localhost:30080/api/v1/subject/update?subjectId=1
//Request Body
{
"name":"Maths",
"studentIds":[],
"examIds": []
}   


//Delete Subject
//Method DELETE
http://localhost:30080/api/v1/subject/delete?subjectId=1


//Student

//Crete Student
//method:POST

http://localhost:30080/api/v1/student/save
{
"name":"Raju",
"subjectId":[2],
"examId":[]
}

//Update Student
http://localhost:30080/api/v1/student/updateStudent?stdentId=1
{
"name":"Raju",
"subjectId":[2],
"examId":[]

}
//Enroll Exam
http://localhost:30080/api/v1/student/enroll?studentId=10

//method :PUT
{
"name":"Raju",
"subjectId":[2],
"examId":[1]

}
//Delete Student
http://localhost:30080/api/v1/student/deleteStudent?stdentId=1

//Mehod:DELETE

//Add Exam 
//Merhod:POST
http://localhost:30080/api/v1/exam/save

{
"subjectId":2,
"studentIds":[1,2]
}


//Update Exam
//method=PUT
http://localhost:30080/api/v1/exam/update?examId=10
{
"subjectId":28,
"studentIds":[10,11]
}


//Delete Exam 
//Metho=DELETE
http://localhost:30080/api/v1/exam/deleExam?examId=1

//Number Api Url
//method GET
http://localhost:30080/api/v1/hidden-feature/40


