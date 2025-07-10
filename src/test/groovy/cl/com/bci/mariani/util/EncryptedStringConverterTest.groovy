package cl.com.bci.mariani.util

import spock.lang.Specification

class EncryptedStringConverterTest extends Specification {

    def converter = new EncryptedStringConverter()

    def "convertToDatabaseColumn should encrypt the string"() {
        given:
        String originalText = "hola-mundo"

        when:
        String encrypted = converter.convertToDatabaseColumn(originalText)

        then:
        encrypted != null
        encrypted != originalText
        encrypted instanceof String
    }

    def "convertToEntityAttribute should decrypt back the original string"() {
        given:
        String originalText = "dato-secreto"
        String encrypted = converter.convertToDatabaseColumn(originalText)

        when:
        String decrypted = converter.convertToEntityAttribute(encrypted)

        then:
        decrypted == originalText
    }

    def "convertToDatabaseColumn should return null when input is null"() {
        expect:
        converter.convertToDatabaseColumn(null) == null
    }

    def "convertToEntityAttribute should return null when input is null"() {
        expect:
        converter.convertToEntityAttribute(null) == null
    }

    def "decrypting an invalid encrypted value should throw RuntimeException"() {
        when:
        converter.convertToEntityAttribute("no-es-un-valor-base64-válido")

        then:
        thrown(RuntimeException)
    }
}
