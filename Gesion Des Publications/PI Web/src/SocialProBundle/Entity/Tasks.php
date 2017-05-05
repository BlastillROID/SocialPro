<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Tasks
 *
 * @ORM\Table(name="tasks", indexes={@ORM\Index(name="sub_project_id", columns={"sub_project_id"})})
 * @ORM\Entity
 */
class Tasks
{
    /**
     * @var integer
     *
     * @ORM\Column(name="task_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $taskId;

    /**
     * @var string
     *
     * @ORM\Column(name="task_name", type="string", length=50, nullable=false)
     */
    private $taskName;

    /**
     * @var string
     *
     * @ORM\Column(name="task_priority", type="string", length=6, nullable=false)
     */
    private $taskPriority;

    /**
     * @var \Tasks
     *
     * @ORM\ManyToOne(targetEntity="Tasks")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="sub_project_id", referencedColumnName="task_id")
     * })
     */
    private $subProject;


}

