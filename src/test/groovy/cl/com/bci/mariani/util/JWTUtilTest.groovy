package cl.com.bci.mariani.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import spock.lang.Specification

class JWTUtilTest extends Specification {
    def "GenerateToken"() {
        given:
        def util = new JWTUtil()
        util.secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf" // suficientemente largo

        when:
        String token = util.generateToken("test@mail.com")

        then:
        token != null
    }
}
