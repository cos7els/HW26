import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String psw = req.getParameter("psw");
        if (!uname.equalsIgnoreCase("") && !psw.equalsIgnoreCase("")) {
            log(uname, psw);
            req.setAttribute("uname", uname);
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/index.html").include(req, resp);
        }
    }

    private void log(String uname, String psw) {
        Path path = Path.of("D:\\IdeaProjects\\HW26\\src\\main\\resources\\login.log");
        try {
            if (!path.toFile().exists()) {
                path.toFile().createNewFile();
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
            String writeString = String.format("%s, username='%s', password='%s'\n", dtf.format(LocalDateTime.now()), uname, psw);
            Files.write(path, writeString.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
