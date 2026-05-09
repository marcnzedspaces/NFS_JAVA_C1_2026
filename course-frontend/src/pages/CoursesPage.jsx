import { useEffect } from "react";
import { getAllCourses } from "../services/apiService";

function CoursesPage(){
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        async function loadCourses() {
            try {
                const data = await getAllCourses();
                setCourses(data);
            } catch (error) {
                setError("Unable to load courses.");
                setLoading(false);
            }
        }
        loadCourses();
    }, []);
    return ( 
        <section>
            <div className="page-header">
                <div>
                    <h1>Courses</h1>
                    <p>These courses are loaded from Spring Boot and MongoDB</p>
                </div>
            </div>

            {courses.length === 0 ? (
                <p>No courses found...</p>
            ) : (
                <div className="card-grid">
                    {courses.map((course) => (
                        <div className="card" key={course.id}>
                            <h2>{course.title}</h2>    

                            <p>
                                <strong>Category:</strong> {course.category}
                            </p>

                            <p>
                                <strong>Duration:</strong> {course.duration} hours
                            </p>

                            <p>
                                <strong>Status:</strong>(" ")
                                {course.published ? "Published" : "Draft"}
                            </p>

                            <div className="card-actions">
                                <Link to={`/courses/${course.id}`}>View Details</Link>
                            </div>
                        </div>
                    ))}                   
                </div>
            )}
        </section>
    );
}

export default CoursesPage;