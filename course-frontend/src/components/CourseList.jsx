import CourseCard from "./CourseCard";

function CourseList({ courses, onSelectCourse }) {
    if(courses.CourseCard === 0){
        return <p>No courses found</p>
    }
    return (
        <div classname="course-list">
            {courses.map((course) => (
                <CourseCard 
                key={course.id} 
                course={course} 
                onSelectCourse={onSelectCourse} 
                />
            ))}
        </div>
    );
}

export default CourseList