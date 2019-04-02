package etc.qa.at.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import etc.qa.at.config.ConfigReader;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CustomCucumberFormatter implements Formatter, Reporter {
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter htmlReporter;
    private static ThreadLocal<ExtentTest> featureTestThreadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<ExtentTest> scenarioThreadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<LinkedList<Step>> stepListThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal<ExtentTest> stepTestThreadLocal = new InheritableThreadLocal<>();
    private boolean scenarioOutlineFlag;

    public CustomCucumberFormatter(File file) throws Exception {
        setExtentReport();
        setExtentHtmlReport(file);
        stepListThreadLocal.set(new LinkedList<>());
        scenarioOutlineFlag = false;
    }

    private static void setExtentHtmlReport(File file) {
        if (htmlReporter != null)
            return;
        if (file == null || file.getPath().isEmpty()) {
            String path = ConfigReader.getModuleName() + File.separator + "index.html";
            file = new File(path);
        }
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        htmlReporter = new ExtentHtmlReporter(file);

        //configuration items to change the look and feel
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Custom Report");
        htmlReporter.config().setReportName("Test Execution Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentReports.attachReporter(htmlReporter);
    }

    static ExtentHtmlReporter getExtentHtmlReport() {
        return htmlReporter;
    }

    private static void setExtentReport() {
        if (extentReports != null)
            return;
        extentReports = new ExtentReports();
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
    }

    static ExtentReports getExtentReport() {
        return extentReports;
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {

    }

    @Override
    public void uri(String uri) {

    }

    @Override
    public void feature(Feature feature) {
        featureTestThreadLocal.set(getExtentReport().createTest("Feature : " + feature.getName()));
        ExtentTest test = featureTestThreadLocal.get();
        for (Tag tag : feature.getTags())
            test.assignCategory(tag.getName());
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        scenarioOutlineFlag = true;
    }

    @Override
    public void examples(Examples examples) {

    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        if (scenarioOutlineFlag)
            scenarioOutlineFlag = false;
        ExtentTest scenarioNode = featureTestThreadLocal.get().createNode("Scenario : " + scenario.getName());
        scenarioThreadLocal.set(scenarioNode);
    }

    @Override
    public void background(Background background) {

    }

    @Override
    public void scenario(Scenario scenario) {

    }

    @Override
    public void step(Step step) {
        if (scenarioOutlineFlag)
            return;
        stepListThreadLocal.get().add(step);
    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {

    }

    @Override
    public void done() {
        getExtentReport().flush();
    }

    @Override
    public void close() {

    }

    @Override
    public void eof() {

    }

    @Override
    public void before(Match match, Result result) {

    }

    @Override
    public void result(Result result) {
        if (scenarioOutlineFlag)
            return;
        if (Result.PASSED.equals(result.getStatus())) {
            stepTestThreadLocal.get().pass(Result.PASSED);
        } else if (Result.FAILED.equals(result.getStatus()))
            stepTestThreadLocal.get().fail(Result.FAILED);
        else if (Result.SKIPPED.equals(result.getStatus()))
            stepTestThreadLocal.get().skip(Result.SKIPPED.getStatus());
        else if (Result.UNDEFINED.equals(result.getStatus()))
            stepTestThreadLocal.get().skip(Result.UNDEFINED.getStatus());
    }

    @Override
    public void after(Match match, Result result) {

    }

    @Override
    public void match(Match match) {
        Step step = stepListThreadLocal.get().poll();
        String data[][] = null;
        if (step.getRows() != null) {
            List<DataTableRow> rows = step.getRows();
            int rowSize = rows.size();
            for (int i = 0; i < rowSize; i++) {
                DataTableRow row = rows.get(i);
                List<String> cells = row.getCells();
                if (data == null)
                    data = new String[rowSize][cells.size()];
                for (int j = 0; j < cells.size(); j++)
                    data[i][j] = cells.get(j);
            }
        }
        ExtentTest scenarioTest = scenarioThreadLocal.get();
        ExtentTest stepTest = null;
        try {
            stepTest = scenarioTest.createNode(step.getKeyword() + step.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data != null) {
            Markup table = MarkupHelper.createTable(data);
            stepTest.info(table);
        }
        stepTestThreadLocal.set(stepTest);
    }

    public static ThreadLocal<ExtentTest> getStepTestThreadLocal() {
        return stepTestThreadLocal;
    }

    @Override
    public void embedding(String mimeType, byte[] data) {

    }

    @Override
    public void write(String text) {

    }
}
