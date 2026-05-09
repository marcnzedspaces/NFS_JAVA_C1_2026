import { useParams } from "react-router-dom";
function CourseDetailPage(){
    const { id } = useParams();

    return ( 
        <section>
            <h1>Course Detail Page</h1>
            <p>Course ID from URL: {id}</p>
        </section>
    );
}

export default CourseDetailPage;