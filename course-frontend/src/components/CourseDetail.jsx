function CourseDetail({course}){
    if(!course){
        return (
        <div className="detail-box">
        <h2>No course selected</h2>
        <p>Click a course card to view details.</p>
        ,</div>
        )        
    }
    return (
        <div className="detail-box">
            <h2>Selected Course</h2>
            
            <p>
                <strong>ID:</strong>{course.id}
            </p>
            <p>
                <strong>Title:</strong>{course.title}
            </p>
            <p>
                <strong>Category:</strong>{course.category}
            </p>
            <p>
                <strong>Duration:</strong>{course.duration} hours
            </p>
            <p>
                <strong>Status:</strong>{course.published ? "published" : "unpublished"}
            </p>
        </div>
    );
}

export default CourseDetail