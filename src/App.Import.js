import './App.css';
import React, { useState } from "react";
import axios from "axios";

function App() {
  axios.defaults.withCredentials = true;

  // const inputFile = document.getElementById("file");
  //const inputVIdeo = document.getElementById("video");
  const [file, setFile] = useState({});
  const [video, setVideo] = useState("");
  const [gif, setGif] = useState("");
  const [caption, setCaption] = useState("");
  const [fileChanged, setFileChanged] = useState(false);


  // 
  const changeVideo = e => {
    // setVideo(() => e.target.files[0]);
    // const videoUrl = URL.createObjectURL(video);
    // console.log(videoUrl);
    // inputVIdeo.setAttribute("src", videoUrl);
    // inputVIdeo.play();
    // const file = e.target.files[0];
    // setVideo(file);
    // console.log(video);
    setFileChanged(true);
    setVideo(e.target.files[0])
    const videoTpye = e.target.files[0].type.includes('video');
    setFile({
      url: URL.createObjectURL(e.target.files[0]),
      video: videoTpye,
    })
  }

  const submitVideo = e => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("file", video);
    console.log(video);

    if (fileChanged) {
      setFileChanged(false);
      setGif('...');
      setCaption('...');

      const config = {
        "Content-Type": 'application/json',
        withCredentials: true
      };

      axios
        .post("http://localhost:5000/upload", formData, config)
        .then(res => {
          setGif(res.data[0]);
          setCaption(res.data[1]);
        })
      console.log(gif);
      console.log(caption);
    }


  }

  return (
    <>
      <form onSubmit={submitVideo} encType="multipart/form-data">
        <input id="file" type="file" accept="video/mp4,video/mkv, 
          video/x-m4v,video/*" onChange={changeVideo} />
        <video muted autoPlay loop id="video"></video>
        {file.video && <video muted autoPlay loop src={file.url} />}
        <button type="submit">생성</button>
      </form>
      <div>{gif}{caption}</div>

    </>
  );
}

export default App;