import { useState } from "react";
import { useNavigate } from "react-router-dom";

function CourseForm({ initialData, onSubmit, buttonText }) {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: initialData?.title || "",
        category: initialData?.category || "",
        duration: initialData?.duration || "",
        published: initialData?.published || false,
    });

    const [errors, setErrors] = useState({});

    function handleChange(event) {
        const { name, value, type, checked } = event.target;

        setFormData({
            ...formData,
            [name]: type === "checkbox" ? checked : value,
        });
    }

    function validateForm() {
        const newErrors = {};

        if (!formData.title.trim()) {
            newErrors.title = "Title is required.";
        } else if (formData.title.trim().length < 3) {
            newErrors.title = "Title must be at least 3 characters.";
        }

        if (!formData.category.trim()) {
            newErrors.category = "Category is required.";
        }

        if (!formData.duration) {
            newErrors.duration = "Duration is required.";
        } else if (Number(formData.duration) <= 0) {
            newErrors.duration = "Duration must be greater than 0.";
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    function handleSubmit(event) {
        event.preventDefault();

        if (!validateForm()) {
            return;
        }

        const courseToSubmit = {
            title: formData.title.trim(),
            category: formData.category.trim(),
            duration: Number(formData.duration),
            published: formData.published,
        };

        onSubmit(courseToSubmit);
    }

    return (
        <form className="form" onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Course Title</label>
                <input
                    name="title"
                    type="text"
                    value={formData.title}
                    onChange={handleChange}
                    placeholder="Example: React Forms"
                />
                {errors.title && <p className="error-message">{errors.title}</p>}
            </div>

            <div className="form-group">
                <label>Category</label>
                <input
                    name="category"
                    type="text"
                    value={formData.category}
                    onChange={handleChange}
                    placeholder="Example: Frontend"
                />
                {errors.category && <p className="error-message">{errors.category}</p>}
            </div>

            <div className="form-group">
                <label>Duration</label>
                <input
                    name="duration"
                    type="number"
                    value={formData.duration}
                    onChange={handleChange}
                    placeholder="Example: 3"
                />
                {errors.duration && <p className="error-message">{errors.duration}</p>}
            </div>

            <div className="form-group checkbox-group">
                <label>
                    <input
                        name="published"
                        type="checkbox"
                        checked={formData.published}
                        onChange={handleChange}
                    />
                    Published
                </label>
            </div>

            <div className="form-actions">
                <button type="submit">{buttonText}</button>

                <button type="button" onClick={() => navigate("/courses")}>
                    Cancel
                </button>
            </div>
        </form>
    );
}

export default CourseForm;