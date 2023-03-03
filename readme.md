# Burger Spring ReadMe
this is the readme file for the group one project. it details the methods that have been created and what they do as well as our test cases with their code coverage

## methods created 

```bash
public List<Integer> getSalaryRangeInYearByJobTitle(String title, String year)
```
this method rewturns a list of salaries wihtin a given year for a given job title

## areas of program that have been tested

```bash
  @Test
    @DisplayName("testing if given id of 10032 eanred a max of 69539")
    void testEmployeeEarnsMaxOf69539(){
        assertTrue(salaryService.getEmployeeHighestSalaryByEmployeeId(10032) == 69539);
    }
```
this testt assess that a specific employee earned a maximum salary of a given number