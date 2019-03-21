@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml

def call() {

Yaml parser = new Yaml()
List example = parser.load(("${WORKSPACE}/pipeline.yaml" as File).text)
return example
}
