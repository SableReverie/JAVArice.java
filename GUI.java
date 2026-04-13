package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login extends Application {
    
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    
    @Override
    public void start(Stage primaryStage) {
        // Create root StackPane
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #191970, #000000);");
        
        // Login Card
        VBox loginCard = new VBox(20);
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(40));
        loginCard.setStyle(
            "-fx-background-color: rgba(255,255,255,0.95);" +
            "-fx-background-radius: 32;" +
            "-fx-border-radius: 32;"
        );
        loginCard.setEffect(new DropShadow(20, Color.BLACK));
        loginCard.setMaxWidth(400);
        
        // Title
        Label titleLabel = new Label("OrgPay Track");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        titleLabel.setTextFill(Color.rgb(25, 25, 112));
        
        Label subtitleLabel = new Label("ICpEP & LSG Payment Management System");
        subtitleLabel.setFont(Font.font("Segoe UI", 14));
        subtitleLabel.setTextFill(Color.GRAY);
        
        // Username Field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-font-size: 14; -fx-padding: 12; -fx-background-radius: 12; -fx-border-radius: 12;");
        
        // Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-font-size: 14; -fx-padding: 12; -fx-background-radius: 12; -fx-border-radius: 12;");
        
        // Login Button
        Button loginBtn = new Button("LOGIN");
        loginBtn.setStyle(
            "-fx-background-color: #191970;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        );
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        
        // Hover effect
        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle(
            "-fx-background-color: #4169E1;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        loginBtn.setOnMouseExited(e -> loginBtn.setStyle(
            "-fx-background-color: #191970;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        
        // Error Label
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setStyle("-fx-font-size: 12;");
        
        loginBtn.setOnAction(e -> {
            if (usernameField.getText().equals(ADMIN_USERNAME) && 
                passwordField.getText().equals(ADMIN_PASSWORD)) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.start(primaryStage);
            } else {
                errorLabel.setText("Invalid username or password!");
            }
        });
        
        loginCard.getChildren().addAll(titleLabel, subtitleLabel, 
            new Separator(), usernameField, passwordField, loginBtn, errorLabel);
        
        root.getChildren().add(loginCard);
        
        Scene scene = new Scene(root, 1280, 720);
        
        // Load CSS - try both possible locations
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            System.out.println("CSS loaded successfully!");
        } catch (Exception e) {
            System.out.println("CSS file not found, using default styles");
        }
        
        primaryStage.setTitle("OrgPay Track - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}


package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu {
    
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f2f5;");
        
        // Title Bar
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(20));
        topBar.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");
        
        Label titleLabel = new Label("OrgPay Track - Main Menu");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.rgb(25, 25, 112));
        topBar.getChildren().add(titleLabel);
        
        // Organization Cards
        HBox centerBox = new HBox(40);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(50));
        
        // ICpEP Card
        VBox icpepCard = createOrgCard(
            "Institute of Computer Engineers\nof the Philippines",
            "ICpEP",
            "#191970", "#4169E1",
            "🤖"
        );
        icpepCard.setOnMouseClicked(e -> {
            OrganizationPanel panel = new OrganizationPanel("ICpEP");
            panel.start(primaryStage);
        });
        
        // LSG Card
        VBox lsgCard = createOrgCard(
            "Local Student Government",
            "LSG",
            "#8B0000", "#DC143C",
            "🏛️"
        );
        lsgCard.setOnMouseClicked(e -> {
            OrganizationPanel panel = new OrganizationPanel("LSG");
            panel.start(primaryStage);
        });
        
        centerBox.getChildren().addAll(icpepCard, lsgCard);
        
        // Logout Button
        Button logoutBtn = new Button("LOGOUT");
        logoutBtn.setStyle(
            "-fx-background-color: #8B0000;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        );
        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle(
            "-fx-background-color: #DC143C;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle(
            "-fx-background-color: #8B0000;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        logoutBtn.setOnAction(e -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        
        HBox bottomBar = new HBox();
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setPadding(new Insets(20));
        bottomBar.getChildren().add(logoutBtn);
        
        root.setTop(topBar);
        root.setCenter(centerBox);
        root.setBottom(bottomBar);
        
        Scene scene = new Scene(root, 1280, 720);
        
        // Load CSS
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS not loaded");
        }
        
        primaryStage.setTitle("OrgPay Track - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createOrgCard(String title, String shortName, 
                                String darkColor, String lightColor, 
                                String emoji) {
        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40, 60, 40, 60));
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 24;" +
            "-fx-border-radius: 24;" +
            "-fx-border-color: " + lightColor + ";" +
            "-fx-border-width: 2;" +
            "-fx-cursor: hand;"
        );
        card.setEffect(new DropShadow(15, Color.rgb(0, 0, 0, 0.1)));
        
        // Hover effects
        card.setOnMouseEntered(e -> {
            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 24;" +
                "-fx-border-radius: 24;" +
                "-fx-border-color: " + lightColor + ";" +
                "-fx-border-width: 3;" +
                "-fx-cursor: hand;"
            );
            card.setScaleX(1.02);
            card.setScaleY(1.02);
        });
        card.setOnMouseExited(e -> {
            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 24;" +
                "-fx-border-radius: 24;" +
                "-fx-border-color: " + lightColor + ";" +
                "-fx-border-width: 2;" +
                "-fx-cursor: hand;"
            );
            card.setScaleX(1.0);
            card.setScaleY(1.0);
        });
        
        Label emojiLabel = new Label(emoji);
        emojiLabel.setFont(Font.font(60));
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web(darkColor));
        titleLabel.setWrapText(true);
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        Label shortLabel = new Label(shortName);
        shortLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        shortLabel.setTextFill(Color.web(lightColor));
        
        Button enterBtn = new Button("ENTER");
        enterBtn.setStyle(
            "-fx-background-color: " + darkColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 30 10 30;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        );
        enterBtn.setOnMouseEntered(ev -> enterBtn.setStyle(
            "-fx-background-color: " + lightColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 30 10 30;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        enterBtn.setOnMouseExited(ev -> enterBtn.setStyle(
            "-fx-background-color: " + darkColor + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 30 10 30;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
        ));
        
        card.getChildren().addAll(emojiLabel, titleLabel, shortLabel, enterBtn);
        return card;
    }
}


package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;

public class OrganizationPanel {
    
    private String orgName;
    private String themeColor;
    private String lightColor;
    private TableView<PaymentRecord> tableView;
    private TableView<Student> studentTableView;
    private ObservableList<PaymentRecord> paymentData;
    private ObservableList<Student> studentData;
    private Label totalLabel, studentsLabel, pendingLabel, collectedLabel;
    
    // Settings
    private Preferences prefs;
    private boolean darkMode = false;
    
    // Payment types with fixed amounts
    private ObservableList<PaymentType> paymentTypes;
    
    private int totalStudents = 0;
    private double totalCollected = 0;
    private int pendingCount = 0;
    
    public OrganizationPanel(String orgName) {
        this.orgName = orgName;
        this.prefs = Preferences.userNodeForPackage(OrganizationPanel.class);
        loadSettings();
        
        if (orgName.equals("ICpEP")) {
            themeColor = "#191970";
            lightColor = "#4169E1";
        } else {
            themeColor = "#8B0000";
            lightColor = "#DC143C";
        }
        
        // Initialize payment types with fixed amounts
        paymentTypes = FXCollections.observableArrayList();
        setupPaymentTypes();
        
        paymentData = FXCollections.observableArrayList();
        studentData = FXCollections.observableArrayList();
        loadSampleData();
    }
    
    private void setupPaymentTypes() {
        if (orgName.equals("ICpEP")) {
            paymentTypes.addAll(
                new PaymentType("Membership Fee", 500.00, "Annual membership fee for ICpEP"),
                new PaymentType("Event Fee - Tech Summit", 300.00, "Annual technology summit"),
                new PaymentType("Event Fee - Workshop", 250.00, "Technical workshop fee"),
                new PaymentType("Donation", 0.00, "Optional donation (custom amount)"),
                new PaymentType("Special Assessment", 200.00, "Special project assessment"),
                new PaymentType("Seminar Fee", 150.00, "Professional seminar fee"),
                new PaymentType("Certification Fee", 100.00, "Certificate processing fee")
            );
        } else {
            paymentTypes.addAll(
                new PaymentType("Student Gov Fee", 300.00, "Annual student government fee"),
                new PaymentType("Event Fee - University Day", 200.00, "University Day celebration"),
                new PaymentType("ID Fee", 150.00, "Student ID processing fee"),
                new PaymentType("Activity Fee", 100.00, "Student activities fee"),
                new PaymentType("Sports Fee", 250.00, "Intramural sports fee"),
                new PaymentType("Cultural Fee", 180.00, "Cultural events fee"),
                new PaymentType("Publication Fee", 120.00, "School publication fee")
            );
        }
    }
    
    private void loadSettings() {
        darkMode = prefs.getBoolean(orgName + "_darkMode", false);
    }
    
    private void saveSettings() {
        prefs.putBoolean(orgName + "_darkMode", darkMode);
    }
    
    private void loadSampleData() {
        // Sample Students Data (pre-existing)
        if (orgName.equals("ICpEP")) {
            studentData.addAll(
                new Student("2024-001", "Juan Dela Cruz", "BSCS", "3rd Year", "09123456789", "delacruz.juan@example.com"),
                new Student("2024-002", "Maria Santos", "BSIT", "2nd Year", "09123456788", "santos.maria@example.com"),
                new Student("2024-003", "Carlos Reyes", "BSCS", "1st Year", "09123456787", "reyes.carlos@example.com"),
                new Student("2024-004", "Anna Cruz", "BSIT", "3rd Year", "09123456786", "cruz.anna@example.com"),
                new Student("2024-005", "Robert Lim", "BSCS", "4th Year", "09123456785", "lim.robert@example.com"),
                new Student("2024-006", "Sofia Garcia", "BSIT", "2nd Year", "09123456784", "garcia.sofia@example.com"),
                new Student("2024-007", "Miguel Tan", "BSCS", "1st Year", "09123456783", "tan.miguel@example.com"),
                new Student("2024-008", "Isabella Cruz", "BSIT", "4th Year", "09123456782", "cruz.isabella@example.com"),
                new Student("2024-009", "Andres Bonifacio", "BSCS", "2nd Year", "09123456781", "bonifacio.andres@example.com"),
                new Student("2024-010", "Jose Rizal", "BSIT", "3rd Year", "09123456780", "rizal.jose@example.com")
            );
            
            // Sample Payment Records (linked to students)
            paymentData.addAll(
                new PaymentRecord(getStudentById("2024-001"), "Membership Fee", 500, "2026-04-01", "PAID"),
                new PaymentRecord(getStudentById("2024-002"), "Membership Fee", 500, "2026-04-03", "PAID"),
                new PaymentRecord(getStudentById("2024-003"), "Event Fee - Tech Summit", 300, "2026-04-05", "PAID"),
                new PaymentRecord(getStudentById("2024-004"), "Membership Fee", 500, "", "PENDING"),
                new PaymentRecord(getStudentById("2024-005"), "Seminar Fee", 150, "", "PENDING"),
                new PaymentRecord(getStudentById("2024-006"), "Membership Fee", 500, "2026-04-08", "PAID"),
                new PaymentRecord(getStudentById("2024-007"), "Workshop Fee", 250, "", "PENDING"),
                new PaymentRecord(getStudentById("2024-008"), "Tech Summit", 300, "2026-04-10", "PAID")
            );
        } else {
            studentData.addAll(
                new Student("2024-001", "Juan Dela Cruz", "BSCS", "3rd Year", "09123456789", "delacruz.juan@example.com"),
                new Student("2024-002", "Maria Santos", "BSIT", "2nd Year", "09123456788", "santos.maria@example.com"),
                new Student("2024-003", "Carlos Reyes", "BSCS", "1st Year", "09123456787", "reyes.carlos@example.com"),
                new Student("2024-004", "Anna Cruz", "BSIT", "3rd Year", "09123456786", "cruz.anna@example.com"),
                new Student("2024-005", "Robert Lim", "BSCS", "4th Year", "09123456785", "lim.robert@example.com"),
                new Student("2024-006", "Sofia Garcia", "BSIT", "2nd Year", "09123456784", "garcia.sofia@example.com")
            );
            
            paymentData.addAll(
                new PaymentRecord(getStudentById("2024-001"), "Student Gov Fee", 300, "2026-04-01", "PAID"),
                new PaymentRecord(getStudentById("2024-002"), "Student Gov Fee", 300, "2026-04-03", "PAID"),
                new PaymentRecord(getStudentById("2024-003"), "University Day", 200, "2026-04-05", "PAID"),
                new PaymentRecord(getStudentById("2024-004"), "Student Gov Fee", 300, "", "PENDING"),
                new PaymentRecord(getStudentById("2024-005"), "Sports Fee", 250, "2026-04-07", "PAID"),
                new PaymentRecord(getStudentById("2024-006"), "Student Gov Fee", 300, "", "PENDING")
            );
        }
        updateTotals();
    }
    
    private Student getStudentById(String id) {
        return studentData.stream()
            .filter(s -> s.getStudentId().equals(id))
            .findFirst()
            .orElse(null);
    }
    
    private void updateTotals() {
        totalStudents = studentData.size();
        totalCollected = paymentData.stream()
            .filter(p -> "PAID".equals(p.getStatus()))
            .mapToDouble(PaymentRecord::getAmountDouble)
            .sum();
        pendingCount = (int) paymentData.stream()
            .filter(p -> "PENDING".equals(p.getStatus()))
            .count();
    }
    
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        applyTheme(root);
        
        HBox header = createHeader(stage);
        root.setTop(header);
        
        VBox sidebar = createSidebar(stage);
        root.setLeft(sidebar);
        
        VBox content = createDashboard();
        root.setCenter(content);
        
        Scene scene = new Scene(root, 1280, 720);
        
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS not loaded");
        }
        
        stage.setTitle(orgName + " - Payment Management System");
        stage.setScene(scene);
        stage.show();
    }
    
    private void applyTheme(BorderPane root) {
        if (darkMode) {
            root.setStyle("-fx-background-color: #1e1e2e;");
        } else {
            root.setStyle("-fx-background-color: #f0f2f5;");
        }
    }
    
    private HBox createHeader(Stage stage) {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 30, 15, 30));
        header.setStyle(darkMode ? 
            "-fx-background-color: #2d2d3d;" :
            "-fx-background-color: " + themeColor + ";");
        header.setSpacing(20);
        
        Button backBtn = createStyledButton("← BACK", "#ffffff", "transparent");
        backBtn.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        });
        
        Label titleLabel = new Label(orgName + " Payment System");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);
        
        Label dateLabel = new Label(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        dateLabel.setStyle("-fx-text-fill: rgba(255,255,255,0.8); -fx-font-size: 14;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        header.getChildren().addAll(backBtn, spacer, titleLabel, dateLabel);
        return header;
    }
    
    private Button createStyledButton(String text, String textColor, String bgColor) {
        Button btn = new Button(text);
        btn.setStyle(
            "-fx-background-color: " + bgColor + ";" +
            "-fx-text-fill: " + textColor + ";" +
            "-fx-font-size: 14;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 8 15 8 15;" +
            "-fx-background-radius: 20;" +
            "-fx-cursor: hand;"
        );
        return btn;
    }
    
    private VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(30, 20, 30, 20));
        sidebar.setStyle(darkMode ?
            "-fx-background-color: #2d2d3d; -fx-border-color: #3d3d4d;" :
            "-fx-background-color: white; -fx-border-color: #e0e0e0;");
        sidebar.setPrefWidth(250);
        
        String[] menuItems = {"📊 Dashboard", "💰 Payments", "➕ Add Payment", "👨‍🎓 Students", "📈 Reports", "⚙️ Settings"};
        
        for (String item : menuItems) {
            Button menuBtn = new Button(item);
            String defaultStyle = darkMode ?
                "-fx-background-color: transparent; -fx-text-fill: #e0e0e0; -fx-font-size: 14; -fx-padding: 12; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;" :
                "-fx-background-color: transparent; -fx-text-fill: #333; -fx-font-size: 14; -fx-padding: 12; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;";
            menuBtn.setStyle(defaultStyle);
            menuBtn.setMaxWidth(Double.MAX_VALUE);
            
            final String menuItem = item;
            menuBtn.setOnAction(e -> handleMenuClick(menuItem, stage));
            
            menuBtn.setOnMouseEntered(ev -> menuBtn.setStyle(
                "-fx-background-color: " + lightColor + "20;" +
                "-fx-text-fill: " + themeColor + ";" +
                "-fx-font-size: 14; -fx-padding: 12; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;"
            ));
            menuBtn.setOnMouseExited(ev -> menuBtn.setStyle(defaultStyle));
            
            sidebar.getChildren().add(menuBtn);
        }
        
        return sidebar;
    }
    
    private void handleMenuClick(String menuItem, Stage stage) {
        switch (menuItem) {
            case "➕ Add Payment":
                showAddPaymentDialog();
                break;
            case "💰 Payments":
                showPaymentsView();
                break;
            case "📊 Dashboard":
                refreshDashboard();
                break;
            case "📈 Reports":
                showReportDialog();
                break;
            case "👨‍🎓 Students":
                showStudentManagement();
                break;
            case "⚙️ Settings":
                showSettingsDialog(stage);
                break;
        }
    }
    
    private void showAddPaymentDialog() {
        Dialog<PaymentRecord> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add New Payment");
        dialog.setHeaderText("Select Student and Payment Type");
        
        ButtonType addButtonType = new ButtonType("Add Payment", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        
        // Student Selection (Dropdown from existing students)
        Label studentLabel = new Label("Select Student:");
        studentLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        
        ComboBox<Student> studentCombo = new ComboBox<>(studentData);
        studentCombo.setPromptText("Choose a student...");
        studentCombo.setStyle("-fx-padding: 10; -fx-background-radius: 10; -fx-border-radius: 10;");
        studentCombo.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getStudentId() + " - " + item.getName() + " (" + item.getCourse() + ")");
                }
            }
        });
        studentCombo.setButtonCell(new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Choose a student...");
                } else {
                    setText(item.getStudentId() + " - " + item.getName());
                }
            }
        });
        
        // Payment Type Selection
        Label paymentLabel = new Label("Select Payment Type:");
        paymentLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        
        ListView<PaymentType> paymentListView = new ListView<>(paymentTypes);
        paymentListView.setPrefHeight(200);
        paymentListView.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
        
        // Custom amount field
        TextField customAmountField = new TextField();
        customAmountField.setPromptText("Enter custom amount (PHP)");
        customAmountField.setStyle("-fx-padding: 10; -fx-background-radius: 10; -fx-border-radius: 10;");
        customAmountField.setDisable(true);
        customAmountField.setVisible(false);
        
        Label amountDisplayLabel = new Label();
        amountDisplayLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        amountDisplayLabel.setTextFill(Color.web(themeColor));
        
        paymentListView.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                if (selected.getName().equals("Donation")) {
                    customAmountField.setDisable(false);
                    customAmountField.setVisible(true);
                    amountDisplayLabel.setText("Enter custom amount: ");
                } else {
                    customAmountField.setDisable(true);
                    customAmountField.setVisible(false);
                    amountDisplayLabel.setText("Amount: ₱" + String.format("%.2f", selected.getAmount()));
                }
            }
        });
        
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setStyle("-fx-padding: 10; -fx-background-radius: 10; -fx-border-radius: 10;");
        
        content.getChildren().addAll(
            studentLabel, studentCombo,
            new Separator(),
            paymentLabel, paymentListView,
            amountDisplayLabel, customAmountField,
            new Separator(),
            new Label("Payment Date:"), datePicker
        );
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().setPrefWidth(450);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Student selectedStudent = studentCombo.getValue();
                PaymentType selectedPayment = paymentListView.getSelectionModel().getSelectedItem();
                
                if (selectedStudent == null) {
                    showAlert("Error", "Please select a student!");
                    return null;
                }
                if (selectedPayment == null) {
                    showAlert("Error", "Please select a payment type!");
                    return null;
                }
                
                double amount;
                if (selectedPayment.getName().equals("Donation") && !customAmountField.getText().isEmpty()) {
                    try {
                        amount = Double.parseDouble(customAmountField.getText());
                    } catch (NumberFormatException e) {
                        showAlert("Error", "Invalid amount entered!");
                        return null;
                    }
                } else {
                    amount = selectedPayment.getAmount();
                }
                
                String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
                return new PaymentRecord(selectedStudent, selectedPayment.getName(), amount, date, "PAID");
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(record -> {
            paymentData.add(0, record);
            updateTotals();
            refreshDashboard();
            showAlert("Success", "Payment added successfully!\nStudent: " + record.getStudent().getName() + "\nAmount: ₱" + record.getAmount());
        });
    }
    
    private void showStudentManagement() {
        Stage studentStage = new Stage();
        studentStage.initModality(Modality.APPLICATION_MODAL);
        studentStage.setTitle(orgName + " - Student Management");
        
        BorderPane root = new BorderPane();
        root.setStyle(darkMode ? "-fx-background-color: #1e1e2e;" : "-fx-background-color: #f0f2f5;");
        
        // Toolbar
        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(15));
        toolbar.setAlignment(Pos.CENTER_LEFT);
        
        Button addStudentBtn = new Button("➕ Add New Student");
        addStudentBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 20;");
        addStudentBtn.setOnAction(e -> showAddStudentDialog(studentStage));
        
        Button refreshBtn = new Button("🔄 Refresh");
        refreshBtn.setStyle("-fx-background-color: " + themeColor + "; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 20;");
        refreshBtn.setOnAction(e -> refreshStudentTable());
        
        toolbar.getChildren().addAll(addStudentBtn, refreshBtn);
        
        // Student Table
        studentTableView = new TableView<>();
        setupStudentTable();
        studentTableView.setItems(studentData);
        studentTableView.setStyle("-fx-background-radius: 15;");
        
        root.setTop(toolbar);
        root.setCenter(studentTableView);
        
        Scene scene = new Scene(root, 900, 500);
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception ex) {}
        
        studentStage.setScene(scene);
        studentStage.show();
    }
    
    private void setupStudentTable() {
        TableColumn<Student, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idCol.setPrefWidth(100);
        
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(180);
        
        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseCol.setPrefWidth(100);
        
        TableColumn<Student, String> yearCol = new TableColumn<>("Year Level");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearCol.setPrefWidth(100);
        
        TableColumn<Student, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactCol.setPrefWidth(120);
        
        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(200);
        
        TableColumn<Student, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setPrefWidth(100);
        actionCol.setCellFactory(param -> new TableCell<Student, Void>() {
            private final Button deleteBtn = new Button("Delete");
            
            {
                deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 5 10;");
                deleteBtn.setOnAction(e -> {
                    Student student = getTableView().getItems().get(getIndex());
                    deleteStudent(student);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        });
        
        studentTableView.getColumns().addAll(idCol, nameCol, courseCol, yearCol, contactCol, emailCol, actionCol);
    }
    
    private void showAddStudentDialog(Stage parentStage) {
        Dialog<Student> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add New Student");
        dialog.setHeaderText("Enter Student Information");
        
        ButtonType addButtonType = new ButtonType("Add Student", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        
        TextField idField = new TextField();
        idField.setPromptText("e.g., 2024-011");
        idField.setStyle("-fx-padding: 10; -fx-background-radius: 10;");
        
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setStyle("-fx-padding: 10; -fx-background-radius: 10;");
        
        ComboBox<String> courseBox = new ComboBox<>();
        courseBox.getItems().addAll("BSCS", "BSIT", "BSIS");
        courseBox.setValue("BSCS");
        
        ComboBox<String> yearBox = new ComboBox<>();
        yearBox.getItems().addAll("1st Year", "2nd Year", "3rd Year", "4th Year");
        yearBox.setValue("1st Year");
        
        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");
        contactField.setStyle("-fx-padding: 10; -fx-background-radius: 10;");
        
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.setStyle("-fx-padding: 10; -fx-background-radius: 10;");
        
        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Full Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Course:"), 0, 2);
        grid.add(courseBox, 1, 2);
        grid.add(new Label("Year Level:"), 0, 3);
        grid.add(yearBox, 1, 3);
        grid.add(new Label("Contact:"), 0, 4);
        grid.add(contactField, 1, 4);
        grid.add(new Label("Email:"), 0, 5);
        grid.add(emailField, 1, 5);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                if (idField.getText().isEmpty() || nameField.getText().isEmpty()) {
                    showAlert("Error", "Student ID and Name are required!");
                    return null;
                }
                return new Student(
                    idField.getText(),
                    nameField.getText(),
                    courseBox.getValue(),
                    yearBox.getValue(),
                    contactField.getText(),
                    emailField.getText()
                );
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(student -> {
            studentData.add(student);
            updateTotals();
            refreshStudentTable();
            refreshDashboard();
            showAlert("Success", "Student added successfully!\n" + student.getName() + " (" + student.getStudentId() + ")");
        });
    }
    
    private void deleteStudent(Student student) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Student");
        confirm.setHeaderText("Delete " + student.getName() + "?");
        confirm.setContentText("This will also delete all payment records for this student. This action cannot be undone.");
        
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                studentData.remove(student);
                paymentData.removeIf(p -> p.getStudent().getStudentId().equals(student.getStudentId()));
                updateTotals();
                refreshStudentTable();
                refreshDashboard();
                showAlert("Deleted", student.getName() + " has been removed.");
            }
        });
    }
    
    private void refreshStudentTable() {
        if (studentTableView != null) {
            studentTableView.setItems(studentData);
            studentTableView.refresh();
        }
    }
    
    private void showPaymentsView() {
        Stage paymentStage = new Stage();
        paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.setTitle(orgName + " - Payment Records");
        
        BorderPane root = new BorderPane();
        root.setStyle(darkMode ? "-fx-background-color: #1e1e2e;" : "-fx-background-color: #f0f2f5;");
        
        HBox searchBox = new HBox(10);
        searchBox.setPadding(new Insets(15));
        searchBox.setAlignment(Pos.CENTER);
        
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 Search by name or ID...");
        searchField.setPrefWidth(300);
        searchField.setStyle("-fx-padding: 10; -fx-background-radius: 20;");
        
        ComboBox<String> filterBox = new ComboBox<>();
        filterBox.getItems().addAll("All", "PAID", "PENDING");
        filterBox.setValue("All");
        
        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: " + themeColor + "; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 20;");
        
        TableView<PaymentRecord> table = new TableView<>();
        setupPaymentTable(table);
        table.setItems(paymentData);
        
        searchBtn.setOnAction(e -> {
            String searchText = searchField.getText().toLowerCase();
            String filter = filterBox.getValue();
            
            ObservableList<PaymentRecord> filtered = FXCollections.observableArrayList();
            for (PaymentRecord record : paymentData) {
                boolean matchesSearch = searchText.isEmpty() || 
                    record.getStudent().getName().toLowerCase().contains(searchText) ||
                    record.getStudent().getStudentId().toLowerCase().contains(searchText);
                boolean matchesFilter = filter.equals("All") || record.getStatus().equals(filter);
                
                if (matchesSearch && matchesFilter) {
                    filtered.add(record);
                }
            }
            table.setItems(filtered);
        });
        
        searchBox.getChildren().addAll(searchField, filterBox, searchBtn);
        
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        bottomBox.setPadding(new Insets(15));
        
        Button exportBtn = new Button("📥 Export to CSV");
        exportBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 20;");
        exportBtn.setOnAction(e -> exportToCSV());
        
        Button closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 20;");
        closeBtn.setOnAction(e -> paymentStage.close());
        
        bottomBox.getChildren().addAll(exportBtn, closeBtn);
        
        root.setTop(searchBox);
        root.setCenter(table);
        root.setBottom(bottomBox);
        
        Scene scene = new Scene(root, 1100, 600);
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception ex) {}
        
        paymentStage.setScene(scene);
        paymentStage.show();
    }
    
    private void setupPaymentTable(TableView<PaymentRecord> table) {
        TableColumn<PaymentRecord, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudent().getStudentId()));
        idCol.setPrefWidth(100);
        
        TableColumn<PaymentRecord, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudent().getName()));
        nameCol.setPrefWidth(150);
        
        TableColumn<PaymentRecord, String> courseCol = new TableColumn<>("Course");
        nameCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudent().getCourse()));
        courseCol.setPrefWidth(100);
        
        TableColumn<PaymentRecord, String> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudent().getYear()));
        yearCol.setPrefWidth(80);
        
        TableColumn<PaymentRecord, String> typeCol = new TableColumn<>("Payment Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        typeCol.setPrefWidth(150);
        
        TableColumn<PaymentRecord, Double> amountCol = new TableColumn<>("Amount (₱)");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setPrefWidth(100);
        
        TableColumn<PaymentRecord, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(100);
        
        TableColumn<PaymentRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(100);
        
        statusCol.setCellFactory(column -> new TableCell<PaymentRecord, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.equals("PAID")) {
                        setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: #d4edda;");
                    } else {
                        setStyle("-fx-text-fill: orange; -fx-font-weight: bold; -fx-background-color: #fff3cd;");
                    }
                }
            }
        });
        
        table.getColumns().addAll(idCol, nameCol, courseCol, yearCol, typeCol, amountCol, dateCol, statusCol);
    }
    
    private void showSettingsDialog(Stage stage) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Settings - " + orgName);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        Label appearanceLabel = new Label("Appearance");
        appearanceLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        
        CheckBox darkModeCheck = new CheckBox("Dark Mode");
        darkModeCheck.setSelected(darkMode);
        
        content.getChildren().addAll(appearanceLabel, darkModeCheck);
        
        ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(content);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButton) {
                darkMode = darkModeCheck.isSelected();
                saveSettings();
                showAlert("Settings Saved", "Please restart the panel for changes to take effect.");
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    private void exportToCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append("Student ID,Name,Course,Year,Payment Type,Amount,Date,Status\n");
        
        for (PaymentRecord record : paymentData) {
            csv.append(String.format("%s,%s,%s,%s,%s,%.2f,%s,%s\n",
                record.getStudent().getStudentId(),
                record.getStudent().getName(),
                record.getStudent().getCourse(),
                record.getStudent().getYear(),
                record.getPaymentType(),
                record.getAmountDouble(),
                record.getDate(),
                record.getStatus()));
        }
        
        javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
        javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
        content.putString(csv.toString());
        clipboard.setContent(content);
        
        showAlert("Export Successful", "Payment data has been copied to clipboard!");
    }
    
    private void showReportDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Report - " + orgName);
        alert.setHeaderText("Summary Report");
        
        double paidAmount = paymentData.stream()
            .filter(p -> "PAID".equals(p.getStatus()))
            .mapToDouble(PaymentRecord::getAmountDouble)
            .sum();
        int paidCount = (int) paymentData.stream().filter(p -> "PAID".equals(p.getStatus())).count();
        int pendingCount = (int) paymentData.stream().filter(p -> "PENDING".equals(p.getStatus())).count();
        
        StringBuilder breakdown = new StringBuilder("\n\n--- Breakdown by Payment Type ---\n");
        paymentTypes.forEach(pt -> {
            double total = paymentData.stream()
                .filter(p -> p.getPaymentType().equals(pt.getName()) && "PAID".equals(p.getStatus()))
                .mapToDouble(PaymentRecord::getAmountDouble)
                .sum();
            if (total > 0) {
                breakdown.append(String.format("%s: ₱%.2f\n", pt.getName(), total));
            }
        });
        
        String report = String.format(
            "Organization: %s\n\n" +
            "Total Students: %d\n" +
            "Total Paid: %d students\n" +
            "Total Pending: %d students\n" +
            "Collection Rate: %.1f%%\n\n" +
            "Total Amount Collected: ₱%.2f\n" +
            "Average Payment: ₱%.2f%s",
            orgName, totalStudents, paidCount, pendingCount,
            totalStudents > 0 ? (paidCount * 100.0 / totalStudents) : 0,
            paidAmount,
            paidCount > 0 ? paidAmount / paidCount : 0,
            breakdown.toString()
        );
        
        alert.setContentText(report);
        alert.showAndWait();
    }
    
    private VBox createDashboard() {
        VBox dashboard = new VBox(20);
        dashboard.setPadding(new Insets(30));
        
        Label welcomeLabel = new Label("Welcome to " + orgName + " Payment Dashboard");
        welcomeLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.web(themeColor));
        
        HBox statsRow = new HBox(20);
        statsRow.setAlignment(Pos.CENTER);
        
        totalLabel = new Label(String.valueOf(totalStudents));
        collectedLabel = new Label(String.format("₱%.2f", totalCollected));
        pendingLabel = new Label(String.valueOf(pendingCount));
        double completionRate = totalStudents > 0 ? ((totalStudents - pendingCount) * 100.0 / totalStudents) : 0;
        Label rateLabel = new Label(String.format("%.1f%%", completionRate));
        
        statsRow.getChildren().addAll(
            createStatCard("👥 Total Students", totalLabel, "Enrolled"),
            createStatCard("💰 Total Collected", collectedLabel, "All Time"),
            createStatCard("⏳ Pending Payments", pendingLabel, "Unpaid"),
            createStatCard("📊 Completion Rate", rateLabel, "Overall")
        );
        
        Label recentLabel = new Label("📋 Recent Payments");
        recentLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        
        tableView = new TableView<>();
        setupPaymentTable(tableView);
        tableView.setItems(paymentData);
        tableView.setPrefHeight(300);
        
        HBox actionBox = new HBox(15);
        actionBox.setAlignment(Pos.CENTER_RIGHT);
        
        Button addBtn = createStyledButton("➕ Add New Payment", "white", themeColor);
        addBtn.setOnAction(e -> showAddPaymentDialog());
        
        Button refreshBtn = createStyledButton("🔄 Refresh", "white", "#6c757d");
        refreshBtn.setOnAction(e -> refreshDashboard());
        
        actionBox.getChildren().addAll(addBtn, refreshBtn);
        
        dashboard.getChildren().addAll(welcomeLabel, statsRow, recentLabel, tableView, actionBox);
        return dashboard;
    }
    
    private VBox createStatCard(String title, Label valueLabel, String subtitle) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setStyle(darkMode ?
            "-fx-background-color: #2d2d3d; -fx-background-radius: 20; -fx-border-color: #3d3d4d; -fx-border-width: 1;" :
            "-fx-background-color: white; -fx-background-radius: 20; -fx-border-color: #e0e0e0; -fx-border-width: 1;");
        card.setPrefWidth(200);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", 13));
        titleLabel.setTextFill(darkMode ? Color.LIGHTGRAY : Color.GRAY);
        
        valueLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        valueLabel.setTextFill(Color.web(themeColor));
        
        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setFont(Font.font("Segoe UI", 11));
        subtitleLabel.setTextFill(darkMode ? Color.GRAY : Color.LIGHTGRAY);
        
        card.getChildren().addAll(titleLabel, valueLabel, subtitleLabel);
        return card;
    }
    
    private void refreshDashboard() {
        updateTotals();
        totalLabel.setText(String.valueOf(totalStudents));
        collectedLabel.setText(String.format("₱%.2f", totalCollected));
        pendingLabel.setText(String.valueOf(pendingCount));
        
        if (tableView != null) {
            tableView.setItems(paymentData);
            tableView.refresh();
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Student Class
    public static class Student {
        private final String studentId;
        private final String name;
        private final String course;
        private final String year;
        private final String contact;
        private final String email;
        
        public Student(String studentId, String name, String course, String year, String contact, String email) {
            this.studentId = studentId;
            this.name = name;
            this.course = course;
            this.year = year;
            this.contact = contact;
            this.email = email;
        }
        
        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public String getCourse() { return course; }
        public String getYear() { return year; }
        public String getContact() { return contact; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return studentId + " - " + name;
        }
    }
    
    // Payment Type Class
    public static class PaymentType {
        private final String name;
        private final double amount;
        private final String description;
        
        public PaymentType(String name, double amount, String description) {
            this.name = name;
            this.amount = amount;
            this.description = description;
        }
        
        public String getName() { return name; }
        public double getAmount() { return amount; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return name + " - ₱" + String.format("%.2f", amount) + "\n   " + description;
        }
    }
    
    // Payment Record Class
    public static class PaymentRecord {
        private final Student student;
        private final String paymentType;
        private final double amount;
        private final String date;
        private final String status;
        
        public PaymentRecord(Student student, String paymentType, double amount, String date, String status) {
            this.student = student;
            this.paymentType = paymentType;
            this.amount = amount;
            this.date = date;
            this.status = status;
        }
        
        public Student getStudent() { return student; }
        public String getPaymentType() { return paymentType; }
        public double getAmountDouble() { return amount; }
        public String getAmount() { return String.format("₱%.2f", amount); }
        public String getDate() { return date; }
        public String getStatus() { return status; }
    }
}


/* style.css - Modern Design for OrgPay Track */

/* Root Styles */
.root {
    -fx-font-family: 'Segoe UI', 'Inter', 'Arial', sans-serif;
    -fx-background-color: #f0f2f5;
}

/* ==================== CARD STYLES ==================== */
.card {
    -fx-background-color: white;
    -fx-background-radius: 24px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 4);
    -fx-padding: 20px;
    -fx-cursor: hand;
}

.card:hover {
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 8);
}

/* Stat Card Specific */
.stat-card {
    -fx-background-color: white;
    -fx-background-radius: 20px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);
    -fx-padding: 25px;
    -fx-alignment: CENTER;
    -fx-border-color: rgba(65, 105, 225, 0.1);
    -fx-border-width: 1px;
    -fx-border-radius: 20px;
}

.stat-card:hover {
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 15, 0, 0, 5);
}

/* ==================== BUTTON STYLES ==================== */
.btn-primary {
    -fx-background-color: linear-gradient(to right, #191970, #4169E1);
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 12px 30px;
    -fx-background-radius: 30px;
    -fx-cursor: hand;
    -fx-effect: dropshadow(gaussian, rgba(65, 105, 225, 0.3), 8, 0, 0, 2);
}

.btn-primary:hover {
    -fx-background-color: linear-gradient(to right, #4169E1, #191970);
    -fx-effect: dropshadow(gaussian, rgba(65, 105, 225, 0.5), 12, 0, 0, 4);
}

.btn-primary:pressed {
    -fx-background-color: linear-gradient(to right, #0f0f50, #2a4aa0);
}

.btn-danger {
    -fx-background-color: linear-gradient(to right, #8B0000, #DC143C);
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 12px 30px;
    -fx-background-radius: 30px;
    -fx-cursor: hand;
    -fx-effect: dropshadow(gaussian, rgba(220, 20, 60, 0.3), 8, 0, 0, 2);
}

.btn-danger:hover {
    -fx-background-color: linear-gradient(to right, #DC143C, #8B0000);
    -fx-effect: dropshadow(gaussian, rgba(220, 20, 60, 0.5), 12, 0, 0, 4);
}

.btn-secondary {
    -fx-background-color: #6c757d;
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 10px 25px;
    -fx-background-radius: 25px;
    -fx-cursor: hand;
}

.btn-secondary:hover {
    -fx-background-color: #5a6268;
}

/* ==================== TEXT FIELD STYLES ==================== */
.text-field, .password-field, .text-area {
    -fx-background-color: white;
    -fx-border-color: #e0e0e0;
    -fx-border-radius: 12px;
    -fx-background-radius: 12px;
    -fx-padding: 12px 15px;
    -fx-font-size: 14px;
    -fx-font-family: 'Segoe UI', 'Arial', sans-serif;
}

.text-field:focused, .password-field:focused, .text-area:focused {
    -fx-border-color: #4169E1;
    -fx-border-width: 2px;
    -fx-effect: dropshadow(gaussian, rgba(65, 105, 225, 0.2), 5, 0, 0, 0);
}

.text-area {
    -fx-border-radius: 12px;
    -fx-background-radius: 12px;
}

/* ==================== TABLE VIEW STYLES ==================== */
.table-view {
    -fx-background-color: white;
    -fx-background-radius: 16px;
    -fx-border-radius: 16px;
    -fx-border-color: #e0e0e0;
    -fx-border-width: 1px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 0);
}

.table-view .column-header-background {
    -fx-background-color: #191970;
    -fx-background-radius: 16px 16px 0 0;
}

.table-view .column-header, .table-view .filler {
    -fx-background-color: transparent;
    -fx-size: 45px;
}

.table-view .column-header .label {
    -fx-text-fill: white;
    -fx-font-weight: bold;
    -fx-font-size: 13px;
}

.table-row-cell {
    -fx-border-color: transparent;
    -fx-table-cell-border-color: transparent;
}

.table-row-cell:odd {
    -fx-background-color: #f8f9fa;
}

.table-row-cell:selected {
    -fx-background-color: #4169E120;
    -fx-border-color: #4169E1;
    -fx-border-width: 0 0 0 3px;
}

.table-row-cell:hover {
    -fx-background-color: #e8f0fe;
}

/* ==================== TAB PANE STYLES ==================== */
.tab-pane {
    -fx-background-color: transparent;
}

.tab-pane .tab-header-area {
    -fx-padding: 15 0 0 0;
}

.tab-pane .tab {
    -fx-background-color: #f0f0f0;
    -fx-padding: 12px 25px;
    -fx-background-radius: 12px 12px 0 0;
    -fx-cursor: hand;
}

.tab-pane .tab:selected {
    -fx-background-color: #191970;
}

.tab-pane .tab:selected .tab-label {
    -fx-text-fill: white;
    -fx-font-weight: bold;
}

.tab-pane .tab .tab-label {
    -fx-text-fill: #666;
    -fx-font-size: 13px;
}

/* ==================== SIDEBAR STYLES ==================== */
.sidebar {
    -fx-background-color: white;
    -fx-border-color: #e0e0e0;
    -fx-border-width: 0 1px 0 0;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 0);
}

.sidebar-item {
    -fx-background-color: transparent;
    -fx-text-fill: #333;
    -fx-font-size: 14px;
    -fx-padding: 14px 20px;
    -fx-alignment: CENTER_LEFT;
    -fx-cursor: hand;
    -fx-border-radius: 12px;
    -fx-background-radius: 12px;
    -fx-font-weight: 500;
}

.sidebar-item:hover {
    -fx-background-color: #4169E120;
    -fx-text-fill: #191970;
}

/* ==================== DIALOG STYLES ==================== */
.dialog-pane {
    -fx-background-color: white;
    -fx-background-radius: 20px;
}

.dialog-pane .header-panel {
    -fx-background-color: #191970;
    -fx-background-radius: 20px 20px 0 0;
}

.dialog-pane .header-panel .label {
    -fx-text-fill: white;
    -fx-font-size: 18px;
    -fx-font-weight: bold;
}

.dialog-pane .content {
    -fx-padding: 20px;
}

/* ==================== LOGIN CARD STYLES ==================== */
.login-card {
    -fx-background-color: white;
    -fx-background-radius: 32px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 30, 0, 0, 10);
    -fx-padding: 40px;
}

/* ==================== PROGRESS BAR STYLES ==================== */
.progress-bar {
    -fx-background-color: #e0e0e0;
    -fx-background-radius: 10px;
}

.progress-bar .bar {
    -fx-background-color: linear-gradient(to right, #191970, #4169E1);
    -fx-background-radius: 10px;
}

/* ==================== SCROLL BAR STYLES ==================== */
.scroll-pane {
    -fx-background-color: transparent;
}

.scroll-bar:vertical {
    -fx-background-color: transparent;
    -fx-pref-width: 8px;
}

.scroll-bar:vertical .thumb {
    -fx-background-color: #c0c0c0;
    -fx-background-radius: 4px;
}

.scroll-bar:vertical .thumb:hover {
    -fx-background-color: #a0a0a0;
}

/* ==================== SEPARATOR STYLES ==================== */
.separator .line {
    -fx-border-color: #e0e0e0;
    -fx-border-width: 1px;
}

/* ==================== LABEL STYLES ==================== */
.label-title {
    -fx-font-size: 28px;
    -fx-font-weight: bold;
    -fx-text-fill: #191970;
}

.label-subtitle {
    -fx-font-size: 14px;
    -fx-text-fill: #6c757d;
}

.label-stat-value {
    -fx-font-size: 32px;
    -fx-font-weight: bold;
}

/* ==================== CHART STYLES ==================== */
.chart {
    -fx-background-color: white;
    -fx-background-radius: 16px;
    -fx-padding: 15px;
}

.chart-plot-background {
    -fx-background-color: transparent;
}

.chart-legend {
    -fx-background-color: transparent;
}

.default-color0.chart-series-line {
    -fx-stroke: #4169E1;
}

.default-color1.chart-series-line {
    -fx-stroke: #DC143C;
}

/* ==================== COMBO BOX STYLES ==================== */
.combo-box {
    -fx-background-color: white;
    -fx-border-color: #e0e0e0;
    -fx-border-radius: 12px;
    -fx-background-radius: 12px;
    -fx-padding: 5px 10px;
}

.combo-box:focused {
    -fx-border-color: #4169E1;
    -fx-border-width: 2px;
}

.combo-box .list-cell {
    -fx-padding: 8px 12px;
}

/* ==================== DATE PICKER STYLES ==================== */
.date-picker {
    -fx-background-color: white;
    -fx-border-color: #e0e0e0;
    -fx-border-radius: 12px;
    -fx-background-radius: 12px;
}

.date-picker:focused {
    -fx-border-color: #4169E1;
    -fx-border-width: 2px;
}

.date-picker .arrow-button {
    -fx-background-color: transparent;
    -fx-cursor: hand;
}

/* ==================== LIST VIEW STYLES ==================== */
.list-view {
    -fx-background-color: white;
    -fx-background-radius: 16px;
    -fx-border-radius: 16px;
    -fx-border-color: #e0e0e0;
    -fx-border-width: 1px;
}

.list-view .list-cell {
    -fx-padding: 12px;
    -fx-font-size: 13px;
}

.list-view .list-cell:odd {
    -fx-background-color: #f8f9fa;
}

.list-view .list-cell:selected {
    -fx-background-color: #4169E120;
    -fx-border-color: #4169E1;
    -fx-border-width: 0 0 0 3px;
}

.list-view .list-cell:hover {
    -fx-background-color: #e8f0fe;
}
