package org.sda.twitter.servlets;

import org.sda.twitter.database.dao.TweetsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "Publish", value = {"/publishTweet"})
public class PublishTweetServlet extends HttpServlet {

    private TweetsDao dao;

    @Override
    public void init() throws ServletException {
        dao = new TweetsDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String user = (String) session.getAttribute("user");
            String tweet = req.getParameter("tweetArea");
            LocalDateTime publishedTime = LocalDateTime.now();
            boolean success = dao.publishTweet(user, tweet, publishedTime);
            if (success) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
