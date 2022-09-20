package ru.job4j.design.srp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.design.srp.AccountingReport.EXCHANGE;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountingReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() * EXCHANGE).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Stepan", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenITReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ITReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Report</title>")
                .append("</head>")
                .append("<body>")
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder().append("[{").append("\"name\":\"Ivan\",")
                .append("\"hired\":").append("{")
                .append("\"year\":").append(worker.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getHired().get(Calendar.MINUTE))
                .append(",").append("\"second\":").append(worker.getHired().get(Calendar.SECOND)).append("},")
                .append("\"fired\":").append("{")
                .append("\"year\":").append(worker.getFired().get(Calendar.YEAR))
                .append(",").append("\"month\":").append(worker.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker.getFired().get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(100.0).append("}]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Disabled
    @Test
    public void whenXMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("    <employees>").append(System.lineSeparator())
                .append("        <fired>").append(dateFormat.format(worker.getFired().getTime())).append("</fired>")
                .append(System.lineSeparator())
                .append("        <hired>").append(dateFormat.format(worker.getHired().getTime())).append("</hired>")
                .append(System.lineSeparator())
                .append("        <name>").append(worker.getName()).append("</name>")
                .append(System.lineSeparator())
                .append("        <salary>").append(worker.getSalary()).append("</salary>")
                .append(System.lineSeparator())
                .append("    </employees>").append(System.lineSeparator()).append("</employees>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}