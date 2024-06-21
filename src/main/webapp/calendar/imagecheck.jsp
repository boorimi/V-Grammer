<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
        // Register JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");
        
        // Open a connection
        conn = DriverManager.getConnection("jdbc:mariadb://183.111.242.22:3306/ds6951", "ds6951", "soldesk802!!");
        
        // SQL query to retrieve member information along with image icon
        String sql = "SELECT m.m_pk, m.m_name, m.m_debut, m.m_birth, i.i_icon " +
                     "FROM haco_member m " +
                     "LEFT JOIN haco_image i ON m.m_pk = i.i_m_pk " +
                     "WHERE m.m_name IS NOT NULL AND (m.m_debut IS NOT NULL OR m.m_birth IS NOT NULL)";
                     
        // Create a prepared statement
        pstmt = conn.prepareStatement(sql);
        
        // Execute the query
        rs = pstmt.executeQuery();

        // Iterate through the result set
        while (rs.next()) {
            String pk = rs.getString("m_pk");
            String name = rs.getString("m_name");
            String debut = rs.getDate("m_debut") != null ? dateFormat.format(rs.getDate("m_debut")) : "N/A";
            String birth = rs.getDate("m_birth") != null ? dateFormat.format(rs.getDate("m_birth")) : "N/A";
            String icon = rs.getString("i_icon");

            // Output member information and image icon
            out.println("<div>");
            out.println("ID: " + pk + "<br>");
            out.println("Name: " + name + "<br>");
            out.println("Debut: " + debut + "<br>");
            out.println("Birth: " + birth + "<br>");
            out.println("Icon URL: " + icon + "<br>");
            if (icon != null && !icon.isEmpty()) {
                out.println("<img src='" + icon + "' alt='Image' style='width: 100px; height: 100px;'><br>");
            }
            out.println("</div><hr>");
        }
    } catch (SQLException se) {
        // Handle errors for JDBC
        se.printStackTrace();
    } catch (Exception e) {
        // Handle errors for Class.forName
        e.printStackTrace();
    } finally {
        // Finally block to close resources
        try {
            if (rs != null) rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
%>
