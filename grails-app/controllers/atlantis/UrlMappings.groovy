package atlantis

class UrlMappings {

    static mappings = {
        post "/$controller(.$format)?"(action:"save")
        post "/u/$id"(controller: 'UrlEntry', action: 'redirect')

        "/"(controller: 'UrlEntry', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
