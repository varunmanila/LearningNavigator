Learning Navigator API

Subject APIs

Create Subject

Endpoint: POST /api/v1/subject/save

URL:

http://localhost:30080/api/v1/subject/save

Request Body:

{
    "name": "Maths",
    "studentIds": [],
    "examIds": []
}

Update Subject

Endpoint: PUT /api/v1/subject/update

URL:

http://localhost:30080/api/v1/subject/update?subjectId=1

Request Body:

{
    "name": "Maths",
    "studentIds": [],
    "examIds": []
}

Delete Subject

Endpoint: DELETE /api/v1/subject/delete

URL:

http://localhost:30080/api/v1/subject/delete?subjectId=1

Student APIs

Create Student

Endpoint: POST /api/v1/student/save

URL:

http://localhost:30080/api/v1/student/save

Request Body:

{
    "name": "Raju",
    "subjectId": [2],
    "examId": []
}

Update Student

Endpoint: PUT /api/v1/student/updateStudent

URL:

http://localhost:30080/api/v1/student/updateStudent?stdentId=1

Request Body:

{
    "name": "Raju",
    "subjectId": [2],
    "examId": []
}

Enroll Student in Exam

Endpoint: PUT /api/v1/student/enroll

URL:

http://localhost:30080/api/v1/student/enroll?studentId=10

Request Body:

{
    "name": "Raju",
    "subjectId": [2],
    "examId": [1]
}

Delete Student

Endpoint: DELETE /api/v1/student/deleteStudent

URL:

http://localhost:30080/api/v1/student/deleteStudent?stdentId=1

Exam APIs

Add Exam

Endpoint: POST /api/v1/exam/save

URL:

http://localhost:30080/api/v1/exam/save

Request Body:

{
    "subjectId": 2,
    "studentIds": [1,2]
}

Update Exam

Endpoint: PUT /api/v1/exam/update

URL:

http://localhost:30080/api/v1/exam/update?examId=10

Request Body:

{
    "subjectId": 28,
    "studentIds": [10,11]
}

Delete Exam

Endpoint: DELETE /api/v1/exam/deleExam

URL:

http://localhost:30080/api/v1/exam/deleExam?examId=1

Miscellaneous APIs

Number API

Endpoint: GET /api/v1/hidden-feature/{number}

URL:

http://localhost:30080/api/v1/hidden-feature/40

Notes

Ensure the server is running on port 30080 before testing these APIs.

Replace {subjectId}, {studentId}, and {examId} with actual IDs when making requests.

Make sure to use appropriate HTTP methods for each endpoint (POST, PUT, DELETE, GET).

