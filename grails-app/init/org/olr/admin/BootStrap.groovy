package org.olr.admin

import grails.core.GrailsApplication
import org.olr.nonadmin.PlayerFile
import org.olr.nonadmin.QuestionFile

class BootStrap {
    GrailsApplication grailsApplication

    def init = { servletContext ->

        println  "tomcat: ${GrailsApplication.config.getProperty('server.tomcat.remote-ip-header')}"

        println  "tomcat: ${GrailsApplication.config.getProperty('server.tomcat.protocol-header')} "


        // populate an admin user
        User adminUser = populateAdmin()
        // populate an regular user ('rock'/'def')
        User userUser = populateUserRock()
//        User userBob = populateUserBob()
//        // put at least one record in QuestionFile table.
//        if (!QuestionFile.count()) {
//            populateQuestionFile(userUser)
//        }
//        if (!PlayerFile.count) {
//            populatePlayerFile(userUser)
//        }
    }
    def destroy = {
    }

    private QuestionFile populateQuestionFile(User owner) {
        def rec01 = new QuestionFile(
            owner: owner,
            questionsJson: '''
{
  "description": "2017 Chapter Competition Countdown Round Problems 1 - 40.",
  "questions_and_answers": [
    {"q": "If <i>f(x) = x<sup>2</sup> − 3</i> and <i>g(x) = 2x + 1</i>, what is <i>f(g(3))</i>?", "a": "46"},
    {"q": "What is the value of `((2^4)^9)/((4^8)^2)`?", "a": "16"},
    {"q": "Robin rolls two fair octahedral dice, each with faces numbered 1 through 8. What is the probability that the sum of the numbers she rolls is 8? Express you answer as a common fraction.", "a": "7/64"},
    {"q": "What is 120% of 45% of 1200?", "a": "648"},
    {"q": "What is the greatest two-digit prime number whose digits are both prime numbers?", "a": "73"},
    {"q": "What is the value of 212<sup>2</sup> − 211<sup>2</sup>?", "a": "423"},
    {"q": "A stalk of corn is 32 inches tall. It grows by 25% of its height each month for the next three months. What is the height of the stalk, in inches, at the end of the 3-month period? Express your answer as a decimal to the nearest tenth.", "a": "62.5 (inches)"},
    {"q": "Three-fourths of the sum <i>x</i> + 3 is four less than twice <i>x</i>. What is the value of <i>x</i>?", "a": "5"},
    {"q": "A rhombus has an area of 84 in<sup>2</sup> and a diagonal of length 14 inches. What is the length, in inches, of the other diagonal?", "a": "12 (inches)"},
    {"q": "`figure-10`How many squares of any size are in this figure consisting of adjacent unit squares surrounding a larger square?", "a": "18 (squares)"},
    {"q": "If <i>a</i>, <i>b</i> and <i>c</i> are positive integers such that <i>a</i> + <i>b</i> + <i>c</i> = <i>7</i>, what is the least possible value of <i>a</i>! + <i>b</i>! + <i>c</i>!?", "a": "10"},
    {"q": "What is the value of the sum `1/9 + 2/9 + 3/9 + 4/9 + 5/9 + 6/9 + 7/9 + 8/9`?", "a": "4"},
    {"q": "`figure-13`If the gray region of the figure shown is a quarter-circle centered at A, what is the probability that a tiny drop of ink that falls at random onto square ABCD lands on the gray region? Express your answer as a common fraction in terms of &pi;.", "a": "&pi;/4"},
    {"q": "The mean of seven of Charlotte’s scores is 80. The mean of three of those scores is 60. What is the mean of the other four scores?", "a": "95"},
    {"q": "What common fraction has a value that is halfway between `13/18` and `5/6`?", "a": "`7/9`"},
    {"q": "If `16^((x+3)) = 2^((5x))`, what is the value of <i>x</i>?", "a": "12"},
    {"q": "What is the value of 1 + 2 − 4 + 8 + 16 − 32?", "a": "-9"},
    {"q": "A person who has <i>a</i> quarters and <i>b</i> nickels has \$5.60 more than a person who has <i>a</i> nickels and <i>b</i> quarters. What is the value of <i>a</i> − <i>b</i>?", "a": "28"},
    {"q": "For how many positive integers <i>m</i> does the line given by <i>y</i> = <i>mx</i> intersect the segment with endpoints (20, 17) and (17, 20)?", "a": "1 (integers)"},
    {"q": "An earthquake that measures <i>x</i> + 1 on the Richter scale is 10 times as strong as an earthquake that measures <i>x</i> on the same scale for any positive number <i>x</i>. How many times stronger is an earthquake that measures 6.9 than one measuring 4.9 on the Richter scale?", "a": "100 (times)"},
    {"q": "At the time 2:58 p.m., the digits displayed on a clock form an arithmetic sequence in the order in which they appear. How many minutes later will the digits displayed next form an arithmetic sequence?", "a": "23 (minutes)"},
    {"q": "What is the value of (1 + 3 + 5 + ... + 2017) − (2 + 4 + 6 + ... + 2016)?", "a": "1009"},
    {"q": "Rebecca uses a \$20 bill to pay for five notebooks that cost \$3.97 each including tax. How many cents should Rebecca receive in change?", "a": "15 (cents)"},
    {"q": "How many permutations of three different letters can be made from the letters of LINES?", "a": "60 (permutations)"},
    {"q": "Jess selects a 3-digit positive integer at random. What is the probability that she selects a number with at least one odd digit? Express your answer as a common fraction.", "a": "8/9"},
    {"q": "What is the value of (5<sup>−1</sup> + 6<sup>−1</sup>)<sup>−1</sup>? Express your answer as a common fraction.", "a": "30/11"},
    {"q": "A 120-pound box contains packages of candy, each with a total weight of 6 ounces. Ignoring the weight of the packaging, how many packages of candy are in the box?", "a": "320 packages"},
    {"q": "For what positive real number <i>x</i>, is (<i>x</i> + 3) the reciprocal of (<i>x</i> − 3)? Express your answer in simplest radical form.", "a": "`sqrt(10)`"},
    {"q": "What is the least integer <i>x</i> for which `x^((x^2 − 9)) = 1`?", "a": "-3"},
    {"q": "The arithmetic mean of ten numbers is 37. What number can be added to the set so that the arithmetic mean of the eleven numbers is 41?", "a": "81"},
    {"q": "`figure-31`This figure shows an equilateral triangle with an inscribed circle of radius 5 cm that is circumscribed around a smaller equilateral triangle. What is the ratio of the area of the smaller triangle to the area of the larger triangle? Express your answer as a common fraction.", "a": "1/4"},
    {"q": "What is the least positive integer that contains each of the digits from 1 to 3 at least once and is divisible by 9?", "a": "1233"},
    {"q": "If 2<i>x</i> + 3 = 1000, what is the value of 4<i>x</i><sup>2</sup> − 9?", "a": "994,000"},
    {"q": "`figure-34`What is the area, in square units, of quadrilateral WXYZ, shown here, with coordinates W(−6, 0), X(0, 5), Y(5, 2) and Z(0, 9)?", "a": "22 (units<sup>2</sup>)"},
    {"q": "A diagonal on one face of a cube measures `4sqrt(3)` inches. What is the total surface area of the cube, in square inches?", "a": "144 (in<sup>2</sup>)"},
    {"q": "The lines given by the equations 2<i>y</i> + <i>ax</i> + 6 = 0 and 4<i>y</i> + <i>x</i> − 30 = 0 are perpendicular. What is the value of <i>a</i>?", "a": "-8"},
    {"q": "Six identical cupcakes are distributed to two friends. If each friend gets at least one cupcake, how many possible outcomes are there?", "a": "5"},
    {"q": "What is the value of the sum 2 + 2(2) + 2(2)(2) + 2(2)(2)(2)?", "a": "30"},
    {"q": "Bill, Melissa and three friends are seated at a round table. Melissa does not sit next to Bill. Assume that two arrangements are considered the same if they are rotations of each other. How many distinct seating arrangements are possible?", "a": "12"},
    {"q": "Given that `x + 1/x = 4`, what is the value of `x^4 + 1/(x^4)`?", "a": ""}
  ],
  "figures": [
    {"name": "figure-10", "src": "figure-10-tiny.png", "val": "<div class=figure><img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJAQMAAABLvZmfAAAABlBMVEX///8jHyCZ/QWRAAAALUlEQVQoz2P4DwMNDA0MCgwcDEwMDEOPieYLBpDo0GP+//8AyGQe8r4YXikKAHXFeVRwVlYzAAAAAElFTkSuQmCC></div>"},
    {"name": "figure-13", "src": "figure-13-tiny.png", "val": "<div class=figure><img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABTCAMAAABXqPIJAAACMVBMVEX///+oqa0jHyAsKStramzPzs7//vrfmlU/PT6b3vokJlbz8/OhoaXb2tuKio5RT1JQTU4wLS6SkpZeXF1JR0gjHzu++v//+eO1tLWZmp2DgoXy///+++uop6ibmpp7en0nNnV0c3NjYWRRTk82MzXn/v/H7vz//fbn5+f9+Nz+9MPCwcIkTZf7049+fH18enqyhW5aWVvPiEgwHytxRikiHyhpKCH4///b/v/R/P/j+f7Z+f688v7P7Pv//O+21u93ve9or+RYod1Smtj+99dLlNW+vb0wb7f/77L84KWZl5f91pTEoo6Ni4z3w31ncnjyuHJubG06VWjgnl1kVU8kIUprV0A2NDYrJyg3HyiSRSdeNCdFICU+JyNLICDT+P7//vzU7/u46Pui4vrE5fmV2fjP5fay3vWt6PR+w+9xuOp4t+D+99X+79L53s7+9s1YjsVeg8X+7sN4msLPvr5BdbxSg7rv2LnvxLhrmrSElLPv2LE0arD+6KxJXadUl6WEkKRegqOMd6JSdaEkVqGEoZ/lw5qKcpk0V5lkiJheapjvzZdEaY4sT4x+h4gkQIjvwoGtmoCbV340T30kOHgwN3YkOHVCW2+ERGtQVWrorGjDkGSEcGOQfF0lKl1QT1y9iFndmlczOFfIiVO+dVJLTE3UjkybcEszPkvDfUZQTURQP0G+dT2+dTg7IDcsIDZsODUzITWiVjAkHzCETy8jHyybUCqQSSmENSJ3JyBYISDtRhi9AAACwklEQVRYw+3Z1VfbYBjH8d/elCwjSb1UqEApMtwZPoaMwbAxhjN3d3d3d3d331+3HjboaTu6juY5Z5zD9yIXuXg+Sd67Jxjvv6lxfREUaHL2bK1W07JjPgKyv4orgxIl50ZP4nd+urIA/u0u/DwNSsRPjJ4ENHdXB9xu39uTPkU5oDUv4HFTry27b1quDGDaXne+93QS/GrdHFtfOD1WGeC483b/xaX+Z//hmfZF/52Fin2ijAH/D56xJ9F76asFIEdFkDwEpH6Pi4Ev+6MVAGq+XCoDulgEdf0G+HN9uxIxnPnmm/2LwB/JLzhVCU41YdSpOJhn5hfMip/z/mA5RihCwNeYBNzUgJGNdYCjBhgxoKcGbNSAykAMGDOJAc5CC+iZTAvE60ALaNqIAWahBWwshRbISQApIHEOWsDFRFrA2AZSwMrSaIEcAaSAxBy0gEonkgIS1wBSwPsCpICVOUEKaASQAmqWRgpIHgNIAbcuhRRQMwcoAanTAFJAoxNJARWLAiVgYw2gBPRcFigBK5cgUgKSoBVBCEiCTgQh4J0vgxDQewQZhIDed74kgIt55xMCbpYJ0AFWgVlACNg4QQYdIGlYpgg6IJ4T0gAyQC2wJhFkgNrIDDJCxUJszO+27KsMAILHRwF/B1Znv3x8/fDRYr+NecdifuvTt+tiRwIkl+AbHxqY8W5LCewPL8cEbWvtz01Vfwb0ORzLigLCAdZ+TS8FUHMhCPDaPw4lBgM2dycTHCIQHtDcXQtv87YlBQOp3+Ji/AG1SsOYtklGmDEkPzFNhS9/YFXPMGBVu1QagTEhy5KC8GOoeB0CqC88WwzO42GDcRz798C3f6waCeBv5FUDFqdzcI0+2hoHfv2GWFMeCPAne4/NRcRV5HasLAI2HCjFUEMb8wcbS6BA5nt5V8/cOpEEusyb6pZgvPEU7Cd8L3ETnnys2wAAAABJRU5ErkJggg==></div>"},
    {"name": "figure-31", "src": "figure-31-tiny.png", "val": "<div class=figure><img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABrCAMAAACL+LbVAAAAaVBMVEX///8jHyDPzs5jYWI0MTHz8/ObmppFQkLn5+eAfn+1tLXb2ttUUlLCwcKop6hycHCOjI0rKCkvLC1cWltAPT1NSkvGxcWvrq55d3g3NDWFhIXs///S0dG8u7uhoKGVk5RvbW5pZ2cnJCT4nKvVAAAEOElEQVRo3u2Zh27cMAyG/WvLlmzfSPd+/4dsnSJlrkRF6TxaFPmBBMkhDm0ufaS7F73oX5SNufsrckDo/oICAN39BU1IgOoOlwLCDN8droS5swZjd7BGGNt1/fL9UC3P+vTch+opugoYugMVfuXzdGxJaUx0Cwf2LwXqWe7IkvJwlGb0y+5yoCI6sqSsQX8b8Ngdooh0G/GDWvbA7MRjSop7NpDvd1QPE3i2+b3yS6ienUpKfri8OwUFg1zooVtLTuBhv5ISTsEZqdtRCVFoZ1tLbsvjji3bmlLV+H0oSD561W4lFYTM1fu0bBmvwk4lJbtyPQXJyWOVcot6NTDY3lhULmGcPEBKc7Z7URA9TRjTT2t69kCctMGiKVODWS/eEkN8tDKqnzF/jKnNswfgnWUtdSPe+WlW9/Yp5vFXQxlmA5iRZf42o4MzwKSex/x5THsPpIGOzW2UgQ8a0Or2ULiN6WgAtzEFebwxMCOL+W1M7QRcNqUgh7dACuzgZ2DQG3iCwQq9l0oJiJajDkehweDbdiUVF7t/gLvfY2oTNmvZiuxynLX0I1m+bGP4QnY543LAtx7bUNBr4CsfWQojzXDCaYOSsmcYyxi3SLYZW+TXm6VUi2NpZGT7BRg2aNJv+LgokK094WGt4QecrMS4jn3yGvi4zu4rwMnjIv/oirfrDF9xLoIV4Ri74U/rRge8ZiiZa4bF66qSsmd2+QRdRbY9MK966xDrxkW2MrcAwpq3Dpmvx0klsp1WvLHQgOGlJNEv+Rrq/lNpKjwX8wVzl7+Xdx7gCpEUSsrjDHfn6HCBKuVuOd813sHY+1alBoMwhxYq3MElxPtWpQBzZv2w6DBT8TWODgDryPWbCgVNJ3fb6BBg+Bkk72bI8B3r1QwTlkv5qSsvdslw23qV3BoAxhlS5avnhukQbVyVAoys6jeOGfrJdYI4RgLci/KFlNXswtobpzqmG5ddRXXMXFUbKo1MvNOQHHRxVXJwt85w9CAN5UDuYuVQV449dOvbDpojgeoGwNwaAIp5Q8ujmyb3t61KPXLbS2KK6YSx0OSlQ2ZGbKoIiqkFAnN//ap0ACzFvGUHOCI1rFe5WxNGOnRa+q1HX0AXGZ16eAffDmvLUwuwJmCk54grS+MEV4RQGY973KezlYG8HIsz7tI7ec3OMXL1a+kJSYRQ+XXG3AypIzCIECq7wyakVk50MoRWYORgEFvsGkwVECphJF8fyXaTrStVGSN7ILbZlSFUwEiyrKsyTDG7QoFIbNQb+KHmSCW7AoTWYuTgASe5WQPRShBKkjCS9v4+l/7AAaavhtCuHiOzAXT+o1kD6FAFoYVbKfxz70LHlCMEfzDHMufLppHm/OzqYZweP+xbIJTSDbpSD2/xKGPM9cfXCYtO14uuk78tHo0D5TpSUK16NY7j/PnHN9Ws7kUv+j/1HSH3IiGhGxHmAAAAAElFTkSuQmCC></div>"},
    {"name": "figure-34", "src": "figure-34-tiny.png", "val": "<div class=figure><img src=data:image/png;base64,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></div>"}
   ]
}
''')
        rec01.save(flush:true)
        if (rec01.hasErrors()) {
            println rec01.errors
            return null
        }
        return rec01
    }

    private PlayerFile populatePlayerFile(User owner) {
        def rec01 = new PlayerFile(
                owner: owner,
                playersJson: '''
{ "description": "GVMS students for CDR on 01/31/18.",
  "players" : [
    { "rank": 1, "name":"Justenn"},
    { "rank": 2, "name":"Allen"},
    { "rank": 3, "name":"Dylan"},
    { "rank": 4, "name":"Eric"},
    { "rank": 5, "name":"Rahul"},
    { "rank": 6, "name":"Charlotte"},
    { "rank": 7, "name":"Stanley"},
    { "rank": 8, "name":"Sriman"},
    { "rank": 9, "name":"Anish"},
    { "rank":10, "name":"Arvant"}
  ]
}
''',
    publik: false)
        rec01.save(flush:true)
        if (rec01.hasErrors()) {
            println rec01.errors
            return null
        }
        return rec01
    }

    private User populateAdmin() {
        def username = grailsApplication.config.getProperty('olr.admin.username')
        println "Creating user: ${username}"
        def adminUser = User.findByUsername(username)
        if (!adminUser) {
            println "Fresh Database. Creating ADMIN user."
            def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
            assert adminRole
            def password = grailsApplication.config.getProperty('olr.admin.password')
            adminUser = new User(username: username, password: password, email: "rrodini@hotmail.com", firstName: "Bob", lastName: "Rodini").save(failOnError: true)
            assert adminUser
            UserRole.create adminUser, adminRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
            return adminUser
        }
        else {
            println "Existing admin user, skipping creation"
        }
        return adminUser
    }

    private User populateUserRock() {
        def username = grailsApplication.config.getProperty('olr.user.username')
        println "Creating regular user"
        def userRock = User.findByUsername(username)
        if (!userRock) {
            println "Fresh Database. Creating REGULAR user."
            def userRole = new Role(authority: "ROLE_USER").save(failOnError: true)
            assert userRole
            def password = grailsApplication.config.getProperty('olr.user.password')
//            def profile = new UserProfile(email: "rrodini@hotmail.com", firstName: "Rocky", lastName: "Racoon").save(failOnError: true)
            userRock = new User(username: username, password: password, email: "rrodini@hotmail.com", firstName: "Bob", lastName: "Rodini").save(failOnError: true)
//            def qf = populateQuestionFile(userRock)
//            userRock.addToQuestionFiles(qf)
            UserRole.create userRock, userRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
        else {
            println "Existing regular user, skipping creation"
        }
        return userRock
    }

    private User populateUserBob() {
        println "Creating regular user"
        def userBob = User.findByUsername("bob")
        if (!userBob) {
            println "Fresh Database. Creating REGULAR user."
            def userRole = Role.findByAuthority('ROLE_USER')
            userBob = new User(username: "bob", password: "def", email: "rrodini@hotmail.com", firstName: "Bob", lastName: "Rodini").save(failOnError: true)
//            def qf = populateQuestionFile(userBob)
//            userBob.addToQuestionFiles(qf)
            UserRole.create userBob, userRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
        else {
            println "Existing regular user, skipping creation"
        }
        return userBob
    }
}

