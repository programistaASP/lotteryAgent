import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by krzysztof on 12.11.17.
 */
@WebServlet("/play")
public class LotteryAgentEntryServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LotteryAgentEntryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Integer> randomNumber = createRandomNumbers();
        writeResponseToClient(resp, randomNumber);
    }

    private Set<Integer> createRandomNumbers() {

        IntStream limit = new Random().ints(1, 50)
                .distinct()
                .limit(6);
        Stream<Integer> boxed = limit
                .boxed();
        return boxed
                .collect(Collectors.toSet());
    }
    /**
     * Wypisanie odpowiedzi za pomocą getWriter()
     * @param resp
     * @param randomNumbers
     */
    private void writeResponseToClient(HttpServletResponse resp, Set<Integer> randomNumbers) throws IOException{
        resp.getWriter()
                .println(randomNumbers.toString());

    }


}
