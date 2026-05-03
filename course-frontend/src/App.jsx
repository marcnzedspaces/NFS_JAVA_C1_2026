import "./App.css";

function App() {
  const courses = [
    {
      id: "1",
      title: "Java Basics",
      category: "Programming",
      duration: 3,
      published: true
    },
    {
      id: "2",
      title: "Spring Boot Basics",
      category: "Backend",
      duration: 5,
      published: true
    },
    {
      id: "3",
      title: "React Fundamentals",
      category: "Frontend",
      duration: 4,
      published: true
    }
  ];

  return (
    <div className="page">
      <h1>Course Frontend</h1>
      <p>This is our first React screen.</p>

      <div className="course-list">
        {courses.map((course) => (
          <div className="card" key={course.id}>
            <h2>{course.title}</h2>
            <p>Category: {course.category}</p>
            <p>Duration: {course.duration} hours</p>
            <p>Status: {course.published ? "Published" : "Draft"}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;

/*
import { useEffect, useState } from 'react';
import './App.css'
import CourseList from './components/CourseList.jsx';
import CourseDetail from './components/CourseDetail.jsx';
import { getCourse } from './services/courseService';

function App() {
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadCourses() {
      try {
        setLoading(true);
        const data = await getCourse();
        setCourses(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    }
    loadCourses();
  }, []);

  return (
    <div className="page">
      <header className="header">
        <h1>Course Catalog</h1>
        <p>Data is loaded from the SoringBoot backend</p>
      </header>

      {loading && <p className="message">Loading courses...</p>}
      
      {error && <p className="error">Error: {error}</p>}

      {!loading && !error && (
        <>
          <p className="summary">Total courses: {courses.length}</p>

          <div className="layout">
            <CourseList courses={courses} onSelectCourse={setSelectedCourse} />
            <CourseDetail course={selectedCourse} />
          </div>
        </>
      )}
    </div>
  )
}

export default App
*/