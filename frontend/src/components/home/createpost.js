export default function CreatePost() {
  return (
    <div>
      <h2>Create a New Post</h2>
      <form>
        <div>
          <label htmlFor="title">Title:</label>
          <input type="text" id="title" name="title" required />    
          <input type="file" id="image" name="image" accept="image/*" />
          </div>
          <button type="submit">post</button>
            </form>
    </div>
  )
};
