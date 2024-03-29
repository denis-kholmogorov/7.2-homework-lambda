import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();
        System.out.println("  Задание №1");
        Collections.sort(staff, Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));

        for (Employee e: staff){
            System.out.println(e);
        }

        //Можно через стрим сделать

         /*
        Collections.sort(staff, ((o1, o2) -> {

            if((o1.getSalary().compareTo(o2.getSalary()) == 0)){
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
            return o1.getSalary().compareTo(o2.getSalary());
        }));*/

        /*staff.stream().sorted((e1, e2) -> {
            if((e1.getSalary().compareTo(e2.getSalary()) == 0)){
                return e1.getName().compareToIgnoreCase(e2.getName());
            }
            return e1.getSalary().compareTo(e2.getSalary());
        }).forEach(System.out::println);*/
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}