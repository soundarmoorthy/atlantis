package atlantis

class UrlMappings {

    static mappings = {
        post "/$controller(.$format)?"(action:"save")

        get "/u/$id"(controller: 'urlEntry', action: 'redirect')

        "/"(controller: 'UrlEntry', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
