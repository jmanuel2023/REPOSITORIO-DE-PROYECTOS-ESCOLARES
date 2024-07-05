import { useParams } from 'react-router-dom'
import { useEffect, useState, useContext } from 'react'
import Header from '../components/Header'
import FormAnswer from '../components/FormAnswer'
import AnswerCard from '../components/AnswerCard'
import UserContext from '../context/UserContext'
const VerFaq = () => {
  const {id} = useParams()
  const {user} = useContext(UserContext)
  const [respuestas, setRespuestas] = useState([])
  const [pregunta, setPregunta] = useState({})
  const [respuesta, setRespuesta] = useState({
    respuesta: '',
    id_faq: id,
    file: null
  })
  const obtenerRespuestas = async () => {
    const respuesta = await fetch("/api/respuesta/respuestas/" + id, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${user.token}`,
      },
    })
    const faq = await respuesta.json()
    const respuestasFormateadas = faq.map(r => {
      return {
        id: r.id,
        respuesta: r.respuesta,
        id_faq: r.pregunta.id,
        nombreArchivo: r.nombreArchivo,

      }
    })
    setRespuestas(respuestasFormateadas)
  }
  useEffect(() => {
    const obtenerFaq = async () => {
      
        const respuesta = await fetch("/api/respuesta/respuestas/" + id, {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${user.token}`,
            },
          })
          const faq = await respuesta.json()
          if(faq.length == 0){
            console.log('no hay respuestas')
            const pregunta = await fetch("/api/faq/" + id, {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.token}`,
              },
            })
            const faq = await pregunta.json()
            setPregunta(faq)
          }else{
            const respuestasFormateadas = faq.map(r => {
              return {
                id: r.id,
                respuesta: r.respuesta,
                id_faq: r.pregunta.id,
                nombreArchivo: r.nombreArchivo,
  
              }
            })
            setRespuestas(respuestasFormateadas)
            setPregunta(faq[0].pregunta)
          }
        

         
          
      
      
    }
    obtenerFaq()
  }, [])
  return (
    <>
      <Header />

      <main className='grid grid-cols-3'>
      <aside className='col-span-1'>
        <FormAnswer res={respuesta} setRespuesta={setRespuesta} obtenerRespuestas={obtenerRespuestas}/>
      </aside>
        <section className='col-span-2'>
        <div className='flex flex-col items-center justify-center gap-5 mb-4'>
        <h1 className='font-bold text-4xl text-center'>{pregunta.pregunta}</h1>
        {
                  pregunta.path_file != null ? pregunta.path_file .split('.').pop() === 'mp4' ? 
                  (  
                    <video src={`/uploads/${faq.path_file}`} controls className="w-50 h-50"></video>
                  ) 
                  :  pregunta.path_file.split('.').pop() === 'mp3' ? 
                  (
                    <audio src={`/uploads/${faq.path_file}`} controls className=""></audio>
                  ) : pregunta.path_file.split('.').pop() === 'jpg' || pregunta.path_file.split('.').pop() === 'jpeg'  ||  pregunta.path_file.split('.').pop() === 'png'?
                   (  
                      <img
                        src={`/uploads/${pregunta.path_file}`}
                        alt={pregunta.pregunta}
                        className="w-60 h-60 "
                      />
                    
                  ) : null :null
                  
          }
        </div>
          <ul className='list-none h-96 overflow-y-auto'>
          {
          respuestas.length === 0 ? (
            <p className='text-center font-semibold text-xl'>No hay respuestas</p>
          ) : (
            respuestas.map((r) => (
            <AnswerCard
            key={r.id}
            respuesta={r} 
            setRespuesta={setRespuesta}
            obtenerRespuestas={obtenerRespuestas}
            />   
               
            ))
          )
          }
        </ul>
        </section>


      </main>

    </>
  )
}

export default VerFaq