def speed(v0, a, t):
    return v0 + (ac * t)

def dis(v, t):
    return v * t

def fall(v0, t):
    return v0 - (9.8 * t)

def wave(v, f):
    return v / f

def force(m, a):
    return m * a

def power(W, t):
    return W / t

def acc(v, v0, t):
    return (v - v0) / t

print("What are you trying to calculate. help for functions.")
func = input()
func = func.lower()
result = 0
if func == "speed":
    print("Enter v0, a, t. As single spaced values.")
    vals = input().split()
    result = speed(int(vals[0]), int(vals[1]), int(vals[2]))
elif func == "dis":
    print("Enter v, t. As single spaced values.")
    vals = input().split()
    result = dis(int(vals[0]), int(vals[1]))
elif func == "fall":
    print("Enter v0, t. As single spaced values.")
    vals = input().split()
    result = fall(int(vals[0]), int(vals[1]))
elif func == "wave":
    print("Enter v, f. As single spaced values.")
    vals = input().split()
    result = wave(int(vals[0]), int(vals[1]))
elif func == "force":
    print("Enter a, m. As single spaced values.")
    vals = input().split()
    result = force(int(vals[0]), int(vals[1]))
elif func == "power":
    print("Enter W, t. As single spaced values.")
    vals = input().split()
    result = power(int(vals[0]), int(vals[1]))
elif func == "acc":
    print("Enter v, v0, t. As single spaced values.")
    vals = input().split()
    result = acc(int(vals[0]), int(vals[1]), int(vals[2]))
elif func == "help":
    print("Functions are.")
    print("speed, dis, fall, wave, force, power, acc")
    print("Capitals dont matter.")
    exit()
print("Your value is")
print(result)
