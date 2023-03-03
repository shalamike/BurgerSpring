# Burger Spring ReadMe
this is the readme file for the group one project. it details the methods that have been created and what they do as well as our test cases with their code coverage

## methods created in the service layer

```bash
public List<Integer> getSalaryRangeInYearByJobTitle(String title, String year)
```
this method returns a list of salaries wihtin a given year for a given job title


```bash
 public Integer getEmployeeHighestSalaryByEmployeeId(Integer id) 
```
this method returns the highest salary earned by a given employ by their id


```bash
public List<Employee>getEmployeeEarningAboveGivenSalary(int salary)
```
this method returns a list of employees who all earns above a given salary value

```bash
public String genderPaygap(String Department)
```
this method returns a string quantifying if there is a pay gap by gender in a given department

```bash
public ArrayList<Employee> getEmployeesByDateAndDepartment(LocalDate beofreDate, LocalDate afterDate, String deptName)
```
this method returns a list of Employees who worked in a given department on a given date

```bash
public List<Employee> getEmployeeByLastName(String lastName)
```
this method returns a list of all employees who share the same last name

```bash
public List<Employee> getEmployeeByLastName(String lastName)
```
this nme

## areas of program that have been tested

```bash
  @Test
    @DisplayName("testing if given id of 10032 eanred a max of 69539")
    void testEmployeeEarnsMaxOf69539(){
        assertTrue(salaryService.getEmployeeHighestSalaryByEmployeeId(10032) == 69539);
    }
```
this test assess that a specific employee earned a maximum salary of a given number

## Testing Coverage

## Contributors
- Michael Shalaby
- Honghao Zheng
- Liam Michael Bramah
- Miguel Camilleri
- Oliver Peter Norgate
- Sina Naseri
- Zac Perkins-Jones