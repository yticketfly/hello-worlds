namespace java com.ervii.knight.primitives.thriftjava
namespace py thriftpython

typedef i32 int // We can use typedef to get pretty names for the types we are using
service WellService
{
  int add(1:int n1, 2:int n2),
}