import com.example.demo.HomeController
import com.example.demo.Video
import com.example.demo.VideoService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@WebMvcTest(HomeController)
class HomeControllerSpec extends Specification{

    @Autowired
    MockMvc mockMvc

    @MockBean
    VideoService videoService

    def "search should return videos contain guery"(){
        given: "A list of videos contain a spec title"
        def query = "Spring"
        def searchResults = [new Video("Spring boot5")]
        given(videoService.searchByTitle(query)).willReturn(searchResults)

        when: "A search request id made"
        def result = mockMvc.perform(get("/search").param("query",query))
        then: "The status is OK and the correct view is returned"
        result.andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(model().attribute("videos",searchResults))
    }
}
