function CourseCard({ course, onSelectCourse }){
    return (
        <div className="card" onClick={() => onSelectCourse(course)}>
            <h2>{course.title}</h2>
            <p><strong>Category:</strong>{course.category}</p>
            <p><strong>Duration:</strong>{course.duration} hours</p>
            <p><strong>Status:</strong>{course.published ? "published" : "unpublished"}</p>
        </div>
    );
}

export default CourseCard