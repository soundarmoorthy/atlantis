package atlantis

class UrlMappings {

    static mappings = {
        post "/$controller(.$format)?"(action:"save")
        get "/$id"(controller: 'urlentry', action: "redirect")

        "/"(controller: 'urlentry', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
