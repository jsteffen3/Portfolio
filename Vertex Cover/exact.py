# Author: Hunter Mann
# Vertex cover, exact min vertex cover

import itertools as it

def checker(graph, proof, v):

    covered = []

    for i, statement in enumerate(proof):
        if statement:
            for vertex in graph[v[i]]:
                if v[i] not in covered:
                    covered.append(v[i])
                if vertex not in covered:
                    covered.append(vertex)
                pass
            pass
        pass

    if len(covered) == len(v):
        return True
    else:
        return False
    pass

def main():


    n = int(input())

    arr = [True, False]

    verts = []
    graph = {}
    for i in range(n):
        edge = input().split()
        if edge[0] not in graph:
            graph.update({edge[0]:[]})
        if edge[1] not in graph:
            graph.update({edge[1]:[]})
        graph[edge[0]].append(edge[1])
        graph[edge[1]].append(edge[0])
        verts.append(edge[0])
        verts.append(edge[1])

    verts = sorted(set(verts)) 
    perm = it.product(arr, repeat=len(verts))

    # print(verts)   
    # print(graph)

    min = float('inf')
    min_cover = []
    min_proof = None
    for proof in perm:
        if checker(graph, proof, verts):
            # record number of Trues
            counter = 0
            for statement in proof:
                if statement:
                    counter += 1
                    pass
                pass
            # compare number of vertecies in cover to previous min
            if (counter < min):
                # record new best min and proof
                min = counter
                min_proof = proof

            pass
        pass
        
    # translate proof to verts
    for i, statement in enumerate(min_proof):
        if statement:
            min_cover.append(verts[i])

    # return min cover vertices
    print(min_cover)
    print(graph)

if __name__ == "__main__":
    main()